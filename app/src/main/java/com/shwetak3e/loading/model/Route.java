package com.shwetak3e.loading.model;

/**
 * Created by shwetakumar on 8/4/17.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;



public class Route {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("shortName")
    @Expose
    private String shortName;
    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("routeHalts")
    @Expose
    private List<RouteHalt> routeHalts = null;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public List<RouteHalt> getRouteHalts() {
        return routeHalts;
    }

    public void setRouteHalts(List<RouteHalt> routeHalts) {
        this.routeHalts = routeHalts;
    }



    public class RouteHalt {

        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("branch")
        @Expose
        private Branch branch;
        @SerializedName("sequence")
        @Expose
        private Integer sequence;
        @SerializedName("loadAllowed")
        @Expose
        private Boolean loadAllowed;
        @SerializedName("unloadAllowed")
        @Expose
        private Boolean unloadAllowed;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public Branch getBranch() {
            return branch;
        }

        public void setBranch(Branch branch) {
            this.branch = branch;
        }

        public Integer getSequence() {
            return sequence;
        }

        public void setSequence(Integer sequence) {
            this.sequence = sequence;
        }

        public Boolean getLoadAllowed() {
            return loadAllowed;
        }

        public void setLoadAllowed(Boolean loadAllowed) {
            this.loadAllowed = loadAllowed;
        }

        public Boolean getUnloadAllowed() {
            return unloadAllowed;
        }

        public void setUnloadAllowed(Boolean unloadAllowed) {
            this.unloadAllowed = unloadAllowed;
        }

    }


    public class StationId {

        @SerializedName("id")
        @Expose
        private String id;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

    }


    public class Branch {

        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("shortCode")
        @Expose
        private String shortCode;
        @SerializedName("stationBranchCode")
        @Expose
        private String stationBranchCode;
        @SerializedName("stationId")
        @Expose
        private Route.StationId stationId;
        @SerializedName("description")
        @Expose
        private String description;
        @SerializedName("address")
        @Expose
        private String address;
        @SerializedName("phoneNumber")
        @Expose
        private String phoneNumber;
        @SerializedName("email")
        @Expose
        private String email;
        @SerializedName("bookingAllowed")
        @Expose
        private Boolean bookingAllowed;
        @SerializedName("deliveryAllowed")
        @Expose
        private Boolean deliveryAllowed;
        @SerializedName("transhipmentAllowed")
        @Expose
        private Boolean transhipmentAllowed;
        @SerializedName("storageChargeAmount")
        @Expose
        private Integer storageChargeAmount;
        @SerializedName("minConsignmentWeight")
        @Expose
        private Integer minConsignmentWeight;
        @SerializedName("bookingCancellationCharge")
        @Expose
        private Integer bookingCancellationCharge;
        @SerializedName("storageNoChargeDuration")
        @Expose
        private Integer storageNoChargeDuration;
        @SerializedName("minSlabForHeavyWeight")
        @Expose
        private Integer minSlabForHeavyWeight;
        @SerializedName("heavyWeightChargePerBooking")
        @Expose
        private Integer heavyWeightChargePerBooking;
        @SerializedName("valueSurcharge")
        @Expose
        private Integer valueSurcharge;
        @SerializedName("valueSurchargePerAmount")
        @Expose
        private Integer valueSurchargePerAmount;
        @SerializedName("discountType")
        @Expose
        private String discountType;
        @SerializedName("maxBookingDiscount")
        @Expose
        private Integer maxBookingDiscount;
        @SerializedName("status")
        @Expose
        private Boolean status;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getShortCode() {
            return shortCode;
        }

        public void setShortCode(String shortCode) {
            this.shortCode = shortCode;
        }

        public String getStationBranchCode() {
            return stationBranchCode;
        }

        public void setStationBranchCode(String stationBranchCode) {
            this.stationBranchCode = stationBranchCode;
        }

        public StationId getStationId() {
            return stationId;
        }

        public void setStationId(StationId stationId) {
            this.stationId = stationId;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getPhoneNumber() {
            return phoneNumber;
        }

        public void setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public Boolean getBookingAllowed() {
            return bookingAllowed;
        }

        public void setBookingAllowed(Boolean bookingAllowed) {
            this.bookingAllowed = bookingAllowed;
        }

        public Boolean getDeliveryAllowed() {
            return deliveryAllowed;
        }

        public void setDeliveryAllowed(Boolean deliveryAllowed) {
            this.deliveryAllowed = deliveryAllowed;
        }

        public Boolean getTranshipmentAllowed() {
            return transhipmentAllowed;
        }

        public void setTranshipmentAllowed(Boolean transhipmentAllowed) {
            this.transhipmentAllowed = transhipmentAllowed;
        }

        public Integer getStorageChargeAmount() {
            return storageChargeAmount;
        }

        public void setStorageChargeAmount(Integer storageChargeAmount) {
            this.storageChargeAmount = storageChargeAmount;
        }

        public Integer getMinConsignmentWeight() {
            return minConsignmentWeight;
        }

        public void setMinConsignmentWeight(Integer minConsignmentWeight) {
            this.minConsignmentWeight = minConsignmentWeight;
        }

        public Integer getBookingCancellationCharge() {
            return bookingCancellationCharge;
        }

        public void setBookingCancellationCharge(Integer bookingCancellationCharge) {
            this.bookingCancellationCharge = bookingCancellationCharge;
        }

        public Integer getStorageNoChargeDuration() {
            return storageNoChargeDuration;
        }

        public void setStorageNoChargeDuration(Integer storageNoChargeDuration) {
            this.storageNoChargeDuration = storageNoChargeDuration;
        }

        public Integer getMinSlabForHeavyWeight() {
            return minSlabForHeavyWeight;
        }

        public void setMinSlabForHeavyWeight(Integer minSlabForHeavyWeight) {
            this.minSlabForHeavyWeight = minSlabForHeavyWeight;
        }

        public Integer getHeavyWeightChargePerBooking() {
            return heavyWeightChargePerBooking;
        }

        public void setHeavyWeightChargePerBooking(Integer heavyWeightChargePerBooking) {
            this.heavyWeightChargePerBooking = heavyWeightChargePerBooking;
        }

        public Integer getValueSurcharge() {
            return valueSurcharge;
        }

        public void setValueSurcharge(Integer valueSurcharge) {
            this.valueSurcharge = valueSurcharge;
        }

        public Integer getValueSurchargePerAmount() {
            return valueSurchargePerAmount;
        }

        public void setValueSurchargePerAmount(Integer valueSurchargePerAmount) {
            this.valueSurchargePerAmount = valueSurchargePerAmount;
        }

        public String getDiscountType() {
            return discountType;
        }

        public void setDiscountType(String discountType) {
            this.discountType = discountType;
        }

        public Integer getMaxBookingDiscount() {
            return maxBookingDiscount;
        }

        public void setMaxBookingDiscount(Integer maxBookingDiscount) {
            this.maxBookingDiscount = maxBookingDiscount;
        }

        public Boolean getStatus() {
            return status;
        }

        public void setStatus(Boolean status) {
            this.status = status;
        }

    }



}







