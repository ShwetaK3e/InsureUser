package com.shwetak3e.loading.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;



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
    @SerializedName("station")
    @Expose
    private Station station;
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

    public Station getStation() {
        return station;
    }

    public void setStation(Station station) {
        this.station = station;
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

    public class Area {

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


    public class DefaultBranch {

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



    public class Region {

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

    public class State {

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

    public class Station {

        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("shortCode")
        @Expose
        private String shortCode;
        @SerializedName("cityCharges")
        @Expose
        private Integer cityCharges;
        @SerializedName("defaultBranch")
        @Expose
        private DefaultBranch defaultBranch;
        @SerializedName("transitTime")
        @Expose
        private Integer transitTime;
        @SerializedName("area")
        @Expose
        private Area area;
        @SerializedName("state")
        @Expose
        private State state;
        @SerializedName("region")
        @Expose
        private Region region;
        @SerializedName("zone")
        @Expose
        private Zone zone;
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

        public Integer getCityCharges() {
            return cityCharges;
        }

        public void setCityCharges(Integer cityCharges) {
            this.cityCharges = cityCharges;
        }

        public DefaultBranch getDefaultBranch() {
            return defaultBranch;
        }

        public void setDefaultBranch(DefaultBranch defaultBranch) {
            this.defaultBranch = defaultBranch;
        }

        public Integer getTransitTime() {
            return transitTime;
        }

        public void setTransitTime(Integer transitTime) {
            this.transitTime = transitTime;
        }

        public Area getArea() {
            return area;
        }

        public void setArea(Area area) {
            this.area = area;
        }

        public State getState() {
            return state;
        }

        public void setState(State state) {
            this.state = state;
        }

        public Region getRegion() {
            return region;
        }

        public void setRegion(Region region) {
            this.region = region;
        }

        public Zone getZone() {
            return zone;
        }

        public void setZone(Zone zone) {
            this.zone = zone;
        }

        public Boolean getStatus() {
            return status;
        }

        public void setStatus(Boolean status) {
            this.status = status;
        }

    }


    public class Zone {

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

}






