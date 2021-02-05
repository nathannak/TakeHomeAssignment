package com.demo.demoapplication.testhelper

import android.widget.TextView
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.demo.demoapplication.di.RepositoryDependencies
import com.demo.demoapplication.model.*
import com.demo.demoapplication.repository.Repository
import com.demo.demoapplication.repository.Resource
import com.demo.demoapplication.repository.Status
import com.demo.demoapplication.view.minutesToClose
import com.demo.demoapplication.view.setDescToTextView
import com.demo.demoapplication.viewmodel.SearchFragmentViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.flow
import org.junit.Assert
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.Mockito

/*
Written by  NN
*/

class UnitTestSuite {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    lateinit var repo: Repository
    lateinit var viewModel: SearchFragmentViewModel

    @Before
    fun setup() {
        repo = Mockito.mock(Repository::class.java)
        viewModel = SearchFragmentViewModel(repo)
    }

    @Test
    fun ViewModel_Flow_Collect_Test() = runBlocking {
        withContext(Dispatchers.Unconfined) {
            Mockito.`when`(
                repo.fetchStores(
                    "37.422740",
                    "-122.139956",
                    "0",
                    "50"
                )
            ).thenReturn(flow {
                emit(Resource(Status.SUCCESS, arrayListOf(storeStub), null))
            })

            viewModel.getStoresFromRepository()

            delay(1000)
            assertTrue(viewModel.liveData.value!!.isNotEmpty())
        }
    }

    @Test
    fun Repository_Dependency_Test() {
        var repository = RepositoryDependencies()

        runBlocking {
            val response = repository.provideRestaurants()
                .getRestaurants("37.422740", "-122.139956", "0", "50")

            val stores = response.body()?.stores
            assertEquals( 50,stores?.size)
        }
    }

    @Test
    fun Binding_Adapter_Minutes_Remaining_Open_Test() {

        val content = TextView(null).minutesToClose("2021-02-01T09:48:14Z|2021-02-09T07:29:59Z")
        assert(content == "open")
    }

    @Test
    fun Binding_Adapter_Minutes_Remaining_Closed_Test() {

        val content = TextView(null).minutesToClose("2021-02-09T07:29:59Z|2021-02-01T09:48:14Z")
        assert(content == "closed")
    }

    @Test
    fun Binding_Adapter_Minutes_Remaining_Duration_Test() {

        val content = TextView(null).minutesToClose("2021-02-01T07:29:59Z|2021-02-01T09:48:14Z")
        assertEquals("2 h, 18 min",content)
    }

    @Test
    fun Binding_Adapter_Set_Description_Test_Length_Greater_Than_One() {

        val content = TextView(null).setDescToTextView("Burgers, Fast Food, Sandwiches, American, Vegetarian, Gluten-Free, Salads, Alcohol")
        assertEquals("Burgers, Fast Food",content)
    }

    @Test
    fun Binding_Adapter_Set_Description_Test_Length_Equals_One() {

        val content = TextView(null).setDescToTextView("Burgers")
        assertEquals("Burgers",content)
    }

    val storeStub = Store(
        average_rating = 0.0,
        business_id = 3289,
        cover_img_url = "https://cdn.doordash.com/media/restaurant/cover/The-Melt.png",
        delivery_fee = 0,
        delivery_fee_monetary_fields = DeliveryFeeMonetaryFields(
            currency = "USD",
            decimal_places = 2,
            display_string = "$0.00",
            unit_amount = 0
        ),
        description = "Burgers,Fast Food, Sandwiches, American, Vegetarian, Gluten-Free, Salads, Alcohol",
        display_delivery_fee = "Free delivery",
        distance_from_consumer = 2.284567578106769,
        extra_sos_delivery_fee = 0,
        extra_sos_delivery_fee_monetary_fields = ExtraSosDeliveryFeeMonetaryFields(
            currency = "USD",
            decimal_places = 2,
            display_string = "$0.00",
            unit_amount = 0
        ),
        header_img_url = "https://cdn.doordash.com/media/store/carousel/The_Melt_Header.jpg",
        id = 62087,
        is_consumer_subscription_eligible = true,
        is_newly_added = false,
        location = Location(lat = 37.44309616088867, lng = -122.17276763916016),
        listOf(
            Menu(
                id = 1861624,
                is_catering = false,
                name = "Doordash Retail",
                popular_items = listOf(
                    PopularItem(
                        description = "More than 1/3 lb of our signature Angus & Wagyu beef, chopped, grilled and filled with loads of melted cheddar and our jalapeño–pickle mix (not too spicy, we promise) with Melt sauce on a toasted, artisan bun (grilled medium, pink & juicy). Includes pickle on the side.",
                        id = 400777918,
                        img_url = "https://img.cdn4dd.com/cdn-cgi/image/fit=contain,width=1200,height=672,format=auto,quality=50/https://doordash-static.s3.amazonaws.com/media/photos/d714f01a-8c0a-474e-8ee1-09de4e894806-retina-large.jpg",
                        name = "MeltBurger",
                        price = 999
                    )
                ),
                subtitle = "The Melt Menu"
            )
        ),
        merchant_promotions =
        listOf(
            MerchantPromotion(
                category_new_store_customers_only = false,
                daypart_constraints = listOf(),
                delivery_fee = 0,
                delivery_fee_monetary_fields = DeliveryFeeMonetaryFieldsX(
                    currency = "USD",
                    decimal_places = 2,
                    display_string = "$0.00",
                    unit_amount = 0
                ),
                id = 38901,
                minimum_subtotal = 0,
                minimum_subtotal_monetary_fields = MinimumSubtotalMonetaryFields(
                    currency = "USD",
                    decimal_places = 2,
                    display_string = "$0.00",
                    unit_amount = 0
                )
            )
        ),
        name = "The Melt",
        next_close_time = "2021-01-31T07:45:00Z",
        next_open_time = "2021-01-31T00:59:20Z",
        num_ratings = 0,
        price_range = 1,
        promotion_delivery_fee = 0,
        service_rate = Any(),
        status = Status(
            asap_available = true,
            asap_minutes_range = listOf(34, 34),
            pickup_available = true,
            scheduled_available = false,
            unavailable_reason = Any()
        ),
        url = "/store/the-melt-palo-alto-62087/"
    )

}