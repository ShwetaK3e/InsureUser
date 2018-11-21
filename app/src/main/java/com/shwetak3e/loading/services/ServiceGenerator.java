package com.shwetak3e.loading.services;


import com.shwetak3e.loading.Utility.Constants;
import com.shwetak3e.loading.model.AddIssueServerResponse;
import com.shwetak3e.loading.model.Booking_1;
import com.shwetak3e.loading.model.Branch;
import com.shwetak3e.loading.model.Route;
import com.shwetak3e.loading.model.Vehicle;
import com.shwetak3e.loading.model.VehicleModel;

import java.io.IOException;
import java.util.List;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;


/**
 * Created by cas on 21-07-2017.
 */

public class ServiceGenerator {


    private static OkHttpClient.Builder okHttpClientBuilder=new OkHttpClient.Builder();
    private  static OkHttpClient okHttpClient =new OkHttpClient();

    private static Retrofit.Builder builder=new Retrofit.Builder()
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .baseUrl(Constants.BASE_URL);

    private static Retrofit retrofit=builder.build();



    public static <T> T createService( Class<T> serviceClass){

        return createService(serviceClass,null);
    }



    private static void buildHttpInterceptor(final String authToken){
        if(authToken!=null){

            okHttpClientBuilder.addInterceptor(new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Request original=chain.request();
                    Request.Builder requestBuilder=original.newBuilder().addHeader("Authorization","Bearer "+ authToken);
                    Request request=requestBuilder.build();
                    return  chain.proceed(request);
                }
            });
            okHttpClient =okHttpClientBuilder.build();
        }
    }


    public static <T> T createService(Class<T> serviceClass, final String authToken){
        if(authToken!=null){
            buildHttpInterceptor(authToken);
        }
        T service = retrofit.create(serviceClass);
        okHttpClient=new OkHttpClient();
        return service;
    }


    public interface Client{


        @Headers("Content-Type: application/json")
        @GET(Constants.BRANCH_LIST)
        rx.Observable<List<Branch>>getBranches();

        @Headers("Content-Type: application/json")
        @GET(Constants.VEHICLE_LIST)
        rx.Observable<List<Vehicle>>getVehiclesAvailable(@Path("branchId") String branchId,@Query("ignoreLoaded") boolean ignoreLoaded);


        @Headers("Content-Type: application/json")
        @GET(Constants.ROUTE_LIST)
        rx.Observable<List<Route>>getRoutes();


        @Headers("Content-Type: application/json")
        @GET(Constants.SHIPMENT_LIST)
        rx.Observable<List<Booking_1>>getShipments(@Query("vehicleId") String vehicleId, @Query("branchId") String branchId, @Query("toPrint") boolean print);


        @Headers("Content-Type: application/json")
        @POST(Constants.VEHICLE+"/{vehicleId}/"+Constants.BRANCH+"/{branchId}/"+Constants.FINALIZE_LOADING)
        Observable<Void> finalizeLoadingSheet(@Path("vehicleId") String vehicleId, @Path("branchId") String branchId, @Body List<UpdateLoadingSheet> updateLoadingSheet );

        @Headers("Content-Type: application/json")
        @POST(Constants.ADD_ITEM_ISSUE)
        Observable<AddIssueServerResponse> addIssue(@Body ItemIssue itemIssue);

        @Headers("Content-Type: application/json")
        @POST(Constants.ADD_VEHILCLE)
        Observable<Vehicle> addVehicle(@Body AddVehicle addVehicle);

        @Headers("Content-Type: application/json")
        @GET(Constants.GET_VEHICLE_MODEL)
        Observable<List<VehicleModel>>getVehicleModel();


/*
        // Change it BookingDetails from List
        @GET(Constants.DELIVERY)
        rx.Observable<List<BookingDetails>> getBooking(@QueryMap Map<String, String> queries);


        // Change the URL to delivery
        @Headers("Content-Type: application/json")
        @POST(Constants.OTP)
        rx.Observable<BookingDetails> updateBooking(@Body ConfirmDelivery confirmDelivery);*/





    }

    public static class UpdateLoadingSheet{
        String id;
        Integer loadedCount;

        public UpdateLoadingSheet(String id, Integer loadedCount) {
            this.id = id;
            this.loadedCount = loadedCount;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public Integer getLoadedCount() {
            return loadedCount;
        }

        public void setLoadedCount(Integer loadedCount) {
            this.loadedCount = loadedCount;
        }
    }









    public static class ItemIssue {

        private String id;
        private String issueType;
        private String issueSourceType;
        private String description;
        private Branch branch;
        private Integer issueItemCount;
        private BookedItem bookedItem;
        private Vehicle vehicle;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getIssueType() {
            return issueType;
        }

        public void setIssueType(String issueType) {
            this.issueType = issueType;
        }

        public String getIssueSourceType() {
            return issueSourceType;
        }

        public void setIssueSourceType(String issueSourceType) {
            this.issueSourceType = issueSourceType;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public Branch getBranch() {
            return branch;
        }

        public void setBranch(Branch branch) {
            this.branch = branch;
        }

        public Integer getIssueItemCount() {
            return issueItemCount;
        }

        public void setIssueItemCount(Integer issueItemCount) {
            this.issueItemCount = issueItemCount;
        }

        public BookedItem getBookedItem() {
            return bookedItem;
        }

        public void setBookedItem(BookedItem bookedItem) {
            this.bookedItem = bookedItem;
        }

        public Vehicle getVehicle() {
            return vehicle;
        }

        public void setVehicle(Vehicle vehicle) {
            this.vehicle = vehicle;
        }

        public static class BookedItem {

            private String id;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

        }


        public static class Branch {



            private String id;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

        }


        public static class Vehicle {



            private String id;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

        }

    }




    public static class AddVehicle {

        private VehicleModel vehicleModel;
        private String vehicleNumber;
        private String vehicleOwnerShipType;
        private String vehicleType;
        private Double vehicleVolume;
        private Double vehiclePermissibleWeight;

        public VehicleModel getVehicleModel() {
            return vehicleModel;
        }

        public void setVehicleModel(VehicleModel vehicleModel) {
            this.vehicleModel = vehicleModel;
        }

        public String getVehicleNumber() {
            return vehicleNumber;
        }

        public void setVehicleNumber(String vehicleNumber) {
            this.vehicleNumber = vehicleNumber;
        }

        public String getVehicleOwnerShipType() {
            return vehicleOwnerShipType;
        }

        public void setVehicleOwnerShipType(String vehicleOwnerShipType) {
            this.vehicleOwnerShipType = vehicleOwnerShipType;
        }

        public String getVehicleType() {
            return vehicleType;
        }

        public void setVehicleType(String vehicleType) {
            this.vehicleType = vehicleType;
        }

        public Double getVehicleVolume() {
            return vehicleVolume;
        }

        public void setVehicleVolume(Double vehicleVolume) {
            this.vehicleVolume = vehicleVolume;
        }

        public Double getVehiclePermissibleWeight() {
            return vehiclePermissibleWeight;
        }

        public void setVehiclePermissibleWeight(Double vehiclePermissibleWeight) {
            this.vehiclePermissibleWeight = vehiclePermissibleWeight;
        }



        public static class VehicleModel {

            private String id;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

        }

    }


}
