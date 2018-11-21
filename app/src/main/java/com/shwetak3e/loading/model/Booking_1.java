package com.shwetak3e.loading.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.LinkedList;
import java.util.List;

public  class Booking_1 implements Parcelable
{

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("refNum")
    @Expose
    private String refNum;
    @SerializedName("bookingDate")
    @Expose
    private String bookingDate;
    @SerializedName("principalCustomer")
    @Expose
    private PrincipalCustomer principalCustomer;
    @SerializedName("shippedFrom")
    @Expose
    private ShippedFrom shippedFrom;
    @SerializedName("shippedTo")
    @Expose
    private ShippedTo shippedTo;
    @SerializedName("consignor")
    @Expose
    private Consignor consignor;
    @SerializedName("consignee")
    @Expose
    private Consignee consignee;
    @SerializedName("paymentType")
    @Expose
    private String paymentType;
    @SerializedName("paymentMode")
    @Expose
    private String paymentMode;
    @SerializedName("paymentComplete")
    @Expose
    private Boolean paymentComplete;
    @SerializedName("invoiceValue")
    @Expose
    private Integer invoiceValue;
    @SerializedName("minimumChargedWeight")
    @Expose
    private Integer minimumChargedWeight;
    @SerializedName("totalActualWeight")
    @Expose
    private Integer totalActualWeight;
    @SerializedName("totalChargedWeight")
    @Expose
    private Integer totalChargedWeight;
    @SerializedName("totalItemCount")
    @Expose
    private Integer totalItemCount;
    @SerializedName("basicFreight")
    @Expose
    private Integer basicFreight;
    @SerializedName("localInwardTransportationCharge")
    @Expose
    private Integer localInwardTransportationCharge;
    @SerializedName("localOutwardTransportationCharge")
    @Expose
    private Integer localOutwardTransportationCharge;
    @SerializedName("valueSurcharge")
    @Expose
    private Integer valueSurcharge;
    @SerializedName("netTotal")
    @Expose
    private Integer netTotal;
    @SerializedName("grandTotal")
    @Expose
    private Integer grandTotal;
    @SerializedName("igst")
    @Expose
    private Integer igst;
    @SerializedName("sgst")
    @Expose
    private Integer sgst;
    @SerializedName("cgst")
    @Expose
    private Integer cgst;
    @SerializedName("discount")
    @Expose
    private Integer discount;
    @SerializedName("discountRemarks")
    @Expose
    private String discountRemarks;
    @SerializedName("createdBy")
    @Expose
    private CreatedBy createdBy;
    @SerializedName("items")
    @Expose
    private List<Item> items = null;
    @SerializedName("componentItems")
    @Expose
    private List<ComponentItem> componentItems = null;
    @SerializedName("invoices")
    @Expose
    private List<Invoice> invoices = null;
    @SerializedName("bookingStatus")
    @Expose
    private String bookingStatus;
    @SerializedName("bookingPriority")
    @Expose
    private String bookingPriority;
    @SerializedName("priorityApproved")
    @Expose
    private Boolean priorityApproved;
    @SerializedName("priorityApprovedBy")
    @Expose
    private PriorityApprovedBy priorityApprovedBy;
    @SerializedName("priorityApprovedDate")
    @Expose
    private String priorityApprovedDate;
    public final static Parcelable.Creator<Booking_1> CREATOR = new Creator<Booking_1>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Booking_1 createFromParcel(Parcel in) {
            Booking_1 instance = new Booking_1();
            instance.id = ((String) in.readValue((String.class.getClassLoader())));
            instance.refNum = ((String) in.readValue((String.class.getClassLoader())));
            instance.bookingDate = ((String) in.readValue((String.class.getClassLoader())));
            instance.principalCustomer = ((PrincipalCustomer) in.readValue((PrincipalCustomer.class.getClassLoader())));
            instance.shippedFrom = ((ShippedFrom) in.readValue((ShippedFrom.class.getClassLoader())));
            instance.shippedTo = ((ShippedTo) in.readValue((ShippedTo.class.getClassLoader())));
            instance.consignor = ((Consignor) in.readValue((Consignor.class.getClassLoader())));
            instance.consignee = ((Consignee) in.readValue((Consignee.class.getClassLoader())));
            instance.paymentType = ((String) in.readValue((String.class.getClassLoader())));
            instance.paymentMode = ((String) in.readValue((String.class.getClassLoader())));
            instance.paymentComplete = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
            instance.invoiceValue = ((Integer) in.readValue((Integer.class.getClassLoader())));
            instance.minimumChargedWeight = ((Integer) in.readValue((Integer.class.getClassLoader())));
            instance.totalActualWeight = ((Integer) in.readValue((Integer.class.getClassLoader())));
            instance.totalChargedWeight = ((Integer) in.readValue((Integer.class.getClassLoader())));
            instance.totalItemCount = ((Integer) in.readValue((Integer.class.getClassLoader())));
            instance.basicFreight = ((Integer) in.readValue((Integer.class.getClassLoader())));
            instance.localInwardTransportationCharge = ((Integer) in.readValue((Integer.class.getClassLoader())));
            instance.localOutwardTransportationCharge = ((Integer) in.readValue((Integer.class.getClassLoader())));
            instance.valueSurcharge = ((Integer) in.readValue((Integer.class.getClassLoader())));
            instance.netTotal = ((Integer) in.readValue((Integer.class.getClassLoader())));
            instance.grandTotal = ((Integer) in.readValue((Integer.class.getClassLoader())));
            instance.igst = ((Integer) in.readValue((Integer.class.getClassLoader())));
            instance.sgst = ((Integer) in.readValue((Integer.class.getClassLoader())));
            instance.cgst = ((Integer) in.readValue((Integer.class.getClassLoader())));
            instance.discount = ((Integer) in.readValue((Integer.class.getClassLoader())));
            instance.discountRemarks = ((String) in.readValue((String.class.getClassLoader())));
            instance.createdBy = ((CreatedBy) in.readValue((CreatedBy.class.getClassLoader())));
            in.readList(instance.items, (Item.class.getClassLoader()));
            in.readList(instance.componentItems, (ComponentItem.class.getClassLoader()));
            in.readList(instance.invoices, (Invoice.class.getClassLoader()));
            instance.bookingStatus = ((String) in.readValue((String.class.getClassLoader())));
            instance.bookingPriority = ((String) in.readValue((String.class.getClassLoader())));
            instance.priorityApproved = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
            instance.priorityApprovedBy = ((PriorityApprovedBy) in.readValue((PriorityApprovedBy.class.getClassLoader())));
            instance.priorityApprovedDate = ((String) in.readValue((String.class.getClassLoader())));
            return instance;
        }

        public Booking_1 [] newArray(int size) {
            return (new Booking_1[size]);
        }

    }
            ;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRefNum() {
        return refNum;
    }

    public void setRefNum(String refNum) {
        this.refNum = refNum;
    }

    public String getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(String bookingDate) {
        this.bookingDate = bookingDate;
    }

    public PrincipalCustomer getPrincipalCustomer() {
        return principalCustomer;
    }

    public void setPrincipalCustomer(PrincipalCustomer principalCustomer) {
        this.principalCustomer = principalCustomer;
    }

    public ShippedFrom getShippedFrom() {
        return shippedFrom;
    }

    public void setShippedFrom(ShippedFrom shippedFrom) {
        this.shippedFrom = shippedFrom;
    }

    public ShippedTo getShippedTo() {
        return shippedTo;
    }

    public void setShippedTo(ShippedTo shippedTo) {
        this.shippedTo = shippedTo;
    }

    public Consignor getConsignor() {
        return consignor;
    }

    public void setConsignor(Consignor consignor) {
        this.consignor = consignor;
    }

    public Consignee getConsignee() {
        return consignee;
    }

    public void setConsignee(Consignee consignee) {
        this.consignee = consignee;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public String getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(String paymentMode) {
        this.paymentMode = paymentMode;
    }

    public Boolean getPaymentComplete() {
        return paymentComplete;
    }

    public void setPaymentComplete(Boolean paymentComplete) {
        this.paymentComplete = paymentComplete;
    }

    public Integer getInvoiceValue() {
        return invoiceValue;
    }

    public void setInvoiceValue(Integer invoiceValue) {
        this.invoiceValue = invoiceValue;
    }

    public Integer getMinimumChargedWeight() {
        return minimumChargedWeight;
    }

    public void setMinimumChargedWeight(Integer minimumChargedWeight) {
        this.minimumChargedWeight = minimumChargedWeight;
    }

    public Integer getTotalActualWeight() {
        return totalActualWeight;
    }

    public void setTotalActualWeight(Integer totalActualWeight) {
        this.totalActualWeight = totalActualWeight;
    }

    public Integer getTotalChargedWeight() {
        return totalChargedWeight;
    }

    public void setTotalChargedWeight(Integer totalChargedWeight) {
        this.totalChargedWeight = totalChargedWeight;
    }

    public Integer getTotalItemCount() {
        return totalItemCount;
    }

    public void setTotalItemCount(Integer totalItemCount) {
        this.totalItemCount = totalItemCount;
    }

    public Integer getBasicFreight() {
        return basicFreight;
    }

    public void setBasicFreight(Integer basicFreight) {
        this.basicFreight = basicFreight;
    }

    public Integer getLocalInwardTransportationCharge() {
        return localInwardTransportationCharge;
    }

    public void setLocalInwardTransportationCharge(Integer localInwardTransportationCharge) {
        this.localInwardTransportationCharge = localInwardTransportationCharge;
    }

    public Integer getLocalOutwardTransportationCharge() {
        return localOutwardTransportationCharge;
    }

    public void setLocalOutwardTransportationCharge(Integer localOutwardTransportationCharge) {
        this.localOutwardTransportationCharge = localOutwardTransportationCharge;
    }

    public Integer getValueSurcharge() {
        return valueSurcharge;
    }

    public void setValueSurcharge(Integer valueSurcharge) {
        this.valueSurcharge = valueSurcharge;
    }

    public Integer getNetTotal() {
        return netTotal;
    }

    public void setNetTotal(Integer netTotal) {
        this.netTotal = netTotal;
    }

    public Integer getGrandTotal() {
        return grandTotal;
    }

    public void setGrandTotal(Integer grandTotal) {
        this.grandTotal = grandTotal;
    }

    public Integer getIgst() {
        return igst;
    }

    public void setIgst(Integer igst) {
        this.igst = igst;
    }

    public Integer getSgst() {
        return sgst;
    }

    public void setSgst(Integer sgst) {
        this.sgst = sgst;
    }

    public Integer getCgst() {
        return cgst;
    }

    public void setCgst(Integer cgst) {
        this.cgst = cgst;
    }

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    public String getDiscountRemarks() {
        return discountRemarks;
    }

    public void setDiscountRemarks(String discountRemarks) {
        this.discountRemarks = discountRemarks;
    }

    public CreatedBy getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(CreatedBy createdBy) {
        this.createdBy = createdBy;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public List<ComponentItem> getComponentItems() {
        return componentItems;
    }

    public void setComponentItems(List<ComponentItem> componentItems) {
        this.componentItems = componentItems;
    }

    public List<Invoice> getInvoices() {
        return invoices;
    }

    public void setInvoices(List<Invoice> invoices) {
        this.invoices = invoices;
    }

    public String getBookingStatus() {
        return bookingStatus;
    }

    public void setBookingStatus(String bookingStatus) {
        this.bookingStatus = bookingStatus;
    }

    public String getBookingPriority() {
        return bookingPriority;
    }

    public void setBookingPriority(String bookingPriority) {
        this.bookingPriority = bookingPriority;
    }

    public Boolean getPriorityApproved() {
        return priorityApproved;
    }

    public void setPriorityApproved(Boolean priorityApproved) {
        this.priorityApproved = priorityApproved;
    }

    public PriorityApprovedBy getPriorityApprovedBy() {
        return priorityApprovedBy;
    }

    public void setPriorityApprovedBy(PriorityApprovedBy priorityApprovedBy) {
        this.priorityApprovedBy = priorityApprovedBy;
    }

    public String getPriorityApprovedDate() {
        return priorityApprovedDate;
    }

    public void setPriorityApprovedDate(String priorityApprovedDate) {
        this.priorityApprovedDate = priorityApprovedDate;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(refNum);
        dest.writeValue(bookingDate);
        dest.writeValue(principalCustomer);
        dest.writeValue(shippedFrom);
        dest.writeValue(shippedTo);
        dest.writeValue(consignor);
        dest.writeValue(consignee);
        dest.writeValue(paymentType);
        dest.writeValue(paymentMode);
        dest.writeValue(paymentComplete);
        dest.writeValue(invoiceValue);
        dest.writeValue(minimumChargedWeight);
        dest.writeValue(totalActualWeight);
        dest.writeValue(totalChargedWeight);
        dest.writeValue(totalItemCount);
        dest.writeValue(basicFreight);
        dest.writeValue(localInwardTransportationCharge);
        dest.writeValue(localOutwardTransportationCharge);
        dest.writeValue(valueSurcharge);
        dest.writeValue(netTotal);
        dest.writeValue(grandTotal);
        dest.writeValue(igst);
        dest.writeValue(sgst);
        dest.writeValue(cgst);
        dest.writeValue(discount);
        dest.writeValue(discountRemarks);
        dest.writeValue(createdBy);
        dest.writeList(items);
        dest.writeList(componentItems);
        dest.writeList(invoices);
        dest.writeValue(bookingStatus);
        dest.writeValue(bookingPriority);
        dest.writeValue(priorityApproved);
        dest.writeValue(priorityApprovedBy);
        dest.writeValue(priorityApprovedDate);
    }

    public int describeContents() {
        return 0;
    }

    


    public static class Area implements Parcelable
    {

        @SerializedName("id")
        @Expose
        private String id;
        public final static Parcelable.Creator<Area> CREATOR = new Creator<Area>() {


            @SuppressWarnings({
                    "unchecked"
            })
            public Area createFromParcel(Parcel in) {
                Area instance = new Area();
                instance.id = ((String) in.readValue((String.class.getClassLoader())));
                return instance;
            }

            public Area[] newArray(int size) {
                return (new Area[size]);
            }

        }
                ;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public void writeToParcel(Parcel dest, int flags) {
            dest.writeValue(id);
        }

        public int describeContents() {
            return 0;
        }

    }




    public  static class ArticleType implements Parcelable
    {

        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("shortCode")
        @Expose
        private String shortCode;
        @SerializedName("length")
        @Expose
        private Float length;
        @SerializedName("width")
        @Expose
        private Float width;
        @SerializedName("height")
        @Expose
        private Float height;
        @SerializedName("sizeUOM")
        @Expose
        private String sizeUOM;
        @SerializedName("minWeight")
        @Expose
        private Float minWeight;
        @SerializedName("weightUOM")
        @Expose
        private String weightUOM;
        @SerializedName("status")
        @Expose
        private Boolean status;
        public final static Parcelable.Creator<ArticleType> CREATOR = new Creator<ArticleType>() {


            @SuppressWarnings({
                    "unchecked"
            })
            public ArticleType createFromParcel(Parcel in) {
                ArticleType instance = new ArticleType();
                instance.id = ((String) in.readValue((String.class.getClassLoader())));
                instance.name = ((String) in.readValue((String.class.getClassLoader())));
                instance.shortCode = ((String) in.readValue((String.class.getClassLoader())));
                instance.length = ((Float) in.readValue((Float.class.getClassLoader())));
                instance.width = ((Float) in.readValue((Float.class.getClassLoader())));
                instance.height = ((Float) in.readValue((Float.class.getClassLoader())));
                instance.sizeUOM = ((String) in.readValue((String.class.getClassLoader())));
                instance.minWeight = ((Float) in.readValue((Float.class.getClassLoader())));
                instance.weightUOM = ((String) in.readValue((String.class.getClassLoader())));
                instance.status = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
                return instance;
            }

            public ArticleType[] newArray(int size) {
                return (new ArticleType[size]);
            }

        }
                ;

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

        public Float getLength() {
            return length;
        }

        public void setLength(Float length) {
            this.length = length;
        }

        public Float getWidth() {
            return width;
        }

        public void setWidth(Float width) {
            this.width = width;
        }

        public Float getHeight() {
            return height;
        }

        public void setHeight(Float height) {
            this.height = height;
        }

        public String getSizeUOM() {
            return sizeUOM;
        }

        public void setSizeUOM(String sizeUOM) {
            this.sizeUOM = sizeUOM;
        }

        public Float getMinWeight() {
            return minWeight;
        }

        public void setMinWeight(Float minWeight) {
            this.minWeight = minWeight;
        }

        public String getWeightUOM() {
            return weightUOM;
        }

        public void setWeightUOM(String weightUOM) {
            this.weightUOM = weightUOM;
        }

        public Boolean getStatus() {
            return status;
        }

        public void setStatus(Boolean status) {
            this.status = status;
        }

        public void writeToParcel(Parcel dest, int flags) {
            dest.writeValue(id);
            dest.writeValue(name);
            dest.writeValue(shortCode);
            dest.writeValue(length);
            dest.writeValue(width);
            dest.writeValue(height);
            dest.writeValue(sizeUOM);
            dest.writeValue(minWeight);
            dest.writeValue(weightUOM);
            dest.writeValue(status);
        }

        public int describeContents() {
            return 0;
        }

    }




    public  static class BookedItem implements Parcelable
    {

        @SerializedName("id")
        @Expose
        private String id;
        public final static Parcelable.Creator<BookedItem> CREATOR = new Creator<BookedItem>() {


            @SuppressWarnings({
                    "unchecked"
            })
            public BookedItem createFromParcel(Parcel in) {
                BookedItem instance = new BookedItem();
                instance.id = ((String) in.readValue((String.class.getClassLoader())));
                return instance;
            }

            public BookedItem[] newArray(int size) {
                return (new BookedItem[size]);
            }

        }
                ;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public void writeToParcel(Parcel dest, int flags) {
            dest.writeValue(id);
        }

        public int describeContents() {
            return 0;
        }

    }



    public  static class BookedItemInstance implements Parcelable
    {

        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("instanceNumber")
        @Expose
        private String instanceNumber;
        public final static Parcelable.Creator<BookedItemInstance> CREATOR = new Creator<BookedItemInstance>() {


            @SuppressWarnings({
                    "unchecked"
            })
            public BookedItemInstance createFromParcel(Parcel in) {
                BookedItemInstance instance = new BookedItemInstance();
                instance.id = ((String) in.readValue((String.class.getClassLoader())));
                instance.instanceNumber = ((String) in.readValue((String.class.getClassLoader())));
                return instance;
            }

            public BookedItemInstance[] newArray(int size) {
                return (new BookedItemInstance[size]);
            }

        }
                ;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getInstanceNumber() {
            return instanceNumber;
        }

        public void setInstanceNumber(String instanceNumber) {
            this.instanceNumber = instanceNumber;
        }

        public void writeToParcel(Parcel dest, int flags) {
            dest.writeValue(id);
            dest.writeValue(instanceNumber);
        }

        public int describeContents() {
            return 0;
        }

    }




    public  static class BookedItemInstance_ implements Parcelable
    {

        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("instanceNumber")
        @Expose
        private String instanceNumber;
        public final static Parcelable.Creator<BookedItemInstance_> CREATOR = new Creator<BookedItemInstance_>() {


            @SuppressWarnings({
                    "unchecked"
            })
            public BookedItemInstance_ createFromParcel(Parcel in) {
                BookedItemInstance_ instance = new BookedItemInstance_();
                instance.id = ((String) in.readValue((String.class.getClassLoader())));
                instance.instanceNumber = ((String) in.readValue((String.class.getClassLoader())));
                return instance;
            }

            public BookedItemInstance_[] newArray(int size) {
                return (new BookedItemInstance_[size]);
            }

        }
                ;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getInstanceNumber() {
            return instanceNumber;
        }

        public void setInstanceNumber(String instanceNumber) {
            this.instanceNumber = instanceNumber;
        }

        public void writeToParcel(Parcel dest, int flags) {
            dest.writeValue(id);
            dest.writeValue(instanceNumber);
        }

        public int describeContents() {
            return 0;
        }

    }

    public  static class BookingOffice implements Parcelable
    {

        @SerializedName("id")
        @Expose
        private String id;
        public final static Parcelable.Creator<BookingOffice> CREATOR = new Creator<BookingOffice>() {


            @SuppressWarnings({
                    "unchecked"
            })
            public BookingOffice createFromParcel(Parcel in) {
                BookingOffice instance = new BookingOffice();
                instance.id = ((String) in.readValue((String.class.getClassLoader())));
                return instance;
            }

            public BookingOffice[] newArray(int size) {
                return (new BookingOffice[size]);
            }

        }
                ;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public void writeToParcel(Parcel dest, int flags) {
            dest.writeValue(id);
        }

        public int describeContents() {
            return 0;
        }

    }




    public  static class Branch implements Parcelable
    {

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
        public final static Parcelable.Creator<Branch> CREATOR = new Creator<Branch>() {


            @SuppressWarnings({
                    "unchecked"
            })
            public Branch createFromParcel(Parcel in) {
                Branch instance = new Branch();
                instance.id = ((String) in.readValue((String.class.getClassLoader())));
                instance.name = ((String) in.readValue((String.class.getClassLoader())));
                instance.shortCode = ((String) in.readValue((String.class.getClassLoader())));
                instance.stationBranchCode = ((String) in.readValue((String.class.getClassLoader())));
                instance.station = ((Station) in.readValue((Station.class.getClassLoader())));
                instance.description = ((String) in.readValue((String.class.getClassLoader())));
                instance.address = ((String) in.readValue((String.class.getClassLoader())));
                instance.phoneNumber = ((String) in.readValue((String.class.getClassLoader())));
                instance.email = ((String) in.readValue((String.class.getClassLoader())));
                instance.bookingAllowed = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
                instance.deliveryAllowed = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
                instance.transhipmentAllowed = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
                instance.storageChargeAmount = ((Integer) in.readValue((Integer.class.getClassLoader())));
                instance.minConsignmentWeight = ((Integer) in.readValue((Integer.class.getClassLoader())));
                instance.bookingCancellationCharge = ((Integer) in.readValue((Integer.class.getClassLoader())));
                instance.storageNoChargeDuration = ((Integer) in.readValue((Integer.class.getClassLoader())));
                instance.minSlabForHeavyWeight = ((Integer) in.readValue((Integer.class.getClassLoader())));
                instance.heavyWeightChargePerBooking = ((Integer) in.readValue((Integer.class.getClassLoader())));
                instance.valueSurcharge = ((Integer) in.readValue((Integer.class.getClassLoader())));
                instance.valueSurchargePerAmount = ((Integer) in.readValue((Integer.class.getClassLoader())));
                instance.discountType = ((String) in.readValue((String.class.getClassLoader())));
                instance.maxBookingDiscount = ((Integer) in.readValue((Integer.class.getClassLoader())));
                instance.status = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
                return instance;
            }

            public Branch[] newArray(int size) {
                return (new Branch[size]);
            }

        }
                ;

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

        public void writeToParcel(Parcel dest, int flags) {
            dest.writeValue(id);
            dest.writeValue(name);
            dest.writeValue(shortCode);
            dest.writeValue(stationBranchCode);
            dest.writeValue(station);
            dest.writeValue(description);
            dest.writeValue(address);
            dest.writeValue(phoneNumber);
            dest.writeValue(email);
            dest.writeValue(bookingAllowed);
            dest.writeValue(deliveryAllowed);
            dest.writeValue(transhipmentAllowed);
            dest.writeValue(storageChargeAmount);
            dest.writeValue(minConsignmentWeight);
            dest.writeValue(bookingCancellationCharge);
            dest.writeValue(storageNoChargeDuration);
            dest.writeValue(minSlabForHeavyWeight);
            dest.writeValue(heavyWeightChargePerBooking);
            dest.writeValue(valueSurcharge);
            dest.writeValue(valueSurchargePerAmount);
            dest.writeValue(discountType);
            dest.writeValue(maxBookingDiscount);
            dest.writeValue(status);
        }

        public int describeContents() {
            return 0;
        }

    }




    public  static class Branch_ implements Parcelable {

        @SerializedName("id")
        @Expose
        private String id;
        public final static Parcelable.Creator<Branch_> CREATOR = new Creator<Branch_>() {


            @SuppressWarnings({
                    "unchecked"
            })
            public Branch_ createFromParcel(Parcel in) {
                Branch_ instance = new Branch_();
                instance.id = ((String) in.readValue((String.class.getClassLoader())));
                return instance;
            }

            public Branch_[] newArray(int size) {
                return (new Branch_[size]);
            }

        };

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public void writeToParcel(Parcel dest, int flags) {
            dest.writeValue(id);
        }

        public int describeContents() {
            return 0;
        }

    }



    public  static class City implements Parcelable
    {

        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("name")
        @Expose
        private String name;
        public final static Parcelable.Creator<City> CREATOR = new Creator<City>() {


            @SuppressWarnings({
                    "unchecked"
            })
            public City createFromParcel(Parcel in) {
                City instance = new City();
                instance.id = ((String) in.readValue((String.class.getClassLoader())));
                instance.name = ((String) in.readValue((String.class.getClassLoader())));
                return instance;
            }

            public City[] newArray(int size) {
                return (new City[size]);
            }

        }
                ;

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

        public void writeToParcel(Parcel dest, int flags) {
            dest.writeValue(id);
            dest.writeValue(name);
        }

        public int describeContents() {
            return 0;
        }

    }




    public  static class City_ implements Parcelable
    {

        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("name")
        @Expose
        private String name;
        public final static Parcelable.Creator<City_> CREATOR = new Creator<City_>() {


            @SuppressWarnings({
                    "unchecked"
            })
            public City_ createFromParcel(Parcel in) {
                City_ instance = new City_();
                instance.id = ((String) in.readValue((String.class.getClassLoader())));
                instance.name = ((String) in.readValue((String.class.getClassLoader())));
                return instance;
            }

            public City_[] newArray(int size) {
                return (new City_[size]);
            }

        }
                ;

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

        public void writeToParcel(Parcel dest, int flags) {
            dest.writeValue(id);
            dest.writeValue(name);
        }

        public int describeContents() {
            return 0;
        }

    }




    public  static class ClosedBy implements Parcelable
    {

        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("email")
        @Expose
        private String email;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("firstName")
        @Expose
        private String firstName;
        @SerializedName("lastName")
        @Expose
        private String lastName;
        public final static Parcelable.Creator<ClosedBy> CREATOR = new Creator<ClosedBy>() {


            @SuppressWarnings({
                    "unchecked"
            })
            public ClosedBy createFromParcel(Parcel in) {
                ClosedBy instance = new ClosedBy();
                instance.id = ((String) in.readValue((String.class.getClassLoader())));
                instance.email = ((String) in.readValue((String.class.getClassLoader())));
                instance.name = ((String) in.readValue((String.class.getClassLoader())));
                instance.firstName = ((String) in.readValue((String.class.getClassLoader())));
                instance.lastName = ((String) in.readValue((String.class.getClassLoader())));
                return instance;
            }

            public ClosedBy[] newArray(int size) {
                return (new ClosedBy[size]);
            }

        }
                ;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public void writeToParcel(Parcel dest, int flags) {
            dest.writeValue(id);
            dest.writeValue(email);
            dest.writeValue(name);
            dest.writeValue(firstName);
            dest.writeValue(lastName);
        }

        public int describeContents() {
            return 0;
        }

    }


    public  static class ComponentItem implements Parcelable
    {

        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("genericComponent")
        @Expose
        private String genericComponent;
        @SerializedName("componentName")
        @Expose
        private String componentName;
        @SerializedName("value")
        @Expose
        private Integer value;
        @SerializedName("priority")
        @Expose
        private Integer priority;
        public final static Parcelable.Creator<ComponentItem> CREATOR = new Creator<ComponentItem>() {


            @SuppressWarnings({
                    "unchecked"
            })
            public ComponentItem createFromParcel(Parcel in) {
                ComponentItem instance = new ComponentItem();
                instance.id = ((String) in.readValue((String.class.getClassLoader())));
                instance.genericComponent = ((String) in.readValue((String.class.getClassLoader())));
                instance.componentName = ((String) in.readValue((String.class.getClassLoader())));
                instance.value = ((Integer) in.readValue((Integer.class.getClassLoader())));
                instance.priority = ((Integer) in.readValue((Integer.class.getClassLoader())));
                return instance;
            }

            public ComponentItem[] newArray(int size) {
                return (new ComponentItem[size]);
            }

        }
                ;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getGenericComponent() {
            return genericComponent;
        }

        public void setGenericComponent(String genericComponent) {
            this.genericComponent = genericComponent;
        }

        public String getComponentName() {
            return componentName;
        }

        public void setComponentName(String componentName) {
            this.componentName = componentName;
        }

        public Integer getValue() {
            return value;
        }

        public void setValue(Integer value) {
            this.value = value;
        }

        public Integer getPriority() {
            return priority;
        }

        public void setPriority(Integer priority) {
            this.priority = priority;
        }

        public void writeToParcel(Parcel dest, int flags) {
            dest.writeValue(id);
            dest.writeValue(genericComponent);
            dest.writeValue(componentName);
            dest.writeValue(value);
            dest.writeValue(priority);
        }

        public int describeContents() {
            return 0;
        }

    }




    public  static class Consignee implements Parcelable
    {

        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("gstin")
        @Expose
        private String gstin;
        @SerializedName("address")
        @Expose
        private String address;
        @SerializedName("city")
        @Expose
        private City_ city;
        @SerializedName("state")
        @Expose
        private State_ state;
        @SerializedName("country")
        @Expose
        private Country_ country;
        @SerializedName("pinCode")
        @Expose
        private String pinCode;
        @SerializedName("mobileNumber")
        @Expose
        private String mobileNumber;
        @SerializedName("customerId")
        @Expose
        private String customerId;
        public final static Parcelable.Creator<Consignee> CREATOR = new Creator<Consignee>() {


            @SuppressWarnings({
                    "unchecked"
            })
            public Consignee createFromParcel(Parcel in) {
                Consignee instance = new Consignee();
                instance.id = ((String) in.readValue((String.class.getClassLoader())));
                instance.name = ((String) in.readValue((String.class.getClassLoader())));
                instance.gstin = ((String) in.readValue((String.class.getClassLoader())));
                instance.address = ((String) in.readValue((String.class.getClassLoader())));
                instance.city = ((City_) in.readValue((City_.class.getClassLoader())));
                instance.state = ((State_) in.readValue((State_.class.getClassLoader())));
                instance.country = ((Country_) in.readValue((Country_.class.getClassLoader())));
                instance.pinCode = ((String) in.readValue((String.class.getClassLoader())));
                instance.mobileNumber = ((String) in.readValue((String.class.getClassLoader())));
                instance.customerId = ((String) in.readValue((String.class.getClassLoader())));
                return instance;
            }

            public Consignee[] newArray(int size) {
                return (new Consignee[size]);
            }

        }
                ;

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

        public String getGstin() {
            return gstin;
        }

        public void setGstin(String gstin) {
            this.gstin = gstin;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public City_ getCity() {
            return city;
        }

        public void setCity(City_ city) {
            this.city = city;
        }

        public State_ getState() {
            return state;
        }

        public void setState(State_ state) {
            this.state = state;
        }

        public Country_ getCountry() {
            return country;
        }

        public void setCountry(Country_ country) {
            this.country = country;
        }

        public String getPinCode() {
            return pinCode;
        }

        public void setPinCode(String pinCode) {
            this.pinCode = pinCode;
        }

        public String getMobileNumber() {
            return mobileNumber;
        }

        public void setMobileNumber(String mobileNumber) {
            this.mobileNumber = mobileNumber;
        }

        public String getCustomerId() {
            return customerId;
        }

        public void setCustomerId(String customerId) {
            this.customerId = customerId;
        }

        public void writeToParcel(Parcel dest, int flags) {
            dest.writeValue(id);
            dest.writeValue(name);
            dest.writeValue(gstin);
            dest.writeValue(address);
            dest.writeValue(city);
            dest.writeValue(state);
            dest.writeValue(country);
            dest.writeValue(pinCode);
            dest.writeValue(mobileNumber);
            dest.writeValue(customerId);
        }

        public int describeContents() {
            return 0;
        }

    }



    public  static class Consignor implements Parcelable
    {

        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("gstin")
        @Expose
        private String gstin;
        @SerializedName("address")
        @Expose
        private String address;
        @SerializedName("city")
        @Expose
        private City city;
        @SerializedName("state")
        @Expose
        private State state;
        @SerializedName("country")
        @Expose
        private Country country;
        @SerializedName("pinCode")
        @Expose
        private String pinCode;
        @SerializedName("mobileNumber")
        @Expose
        private String mobileNumber;
        @SerializedName("customerId")
        @Expose
        private String customerId;
        public final static Parcelable.Creator<Consignor> CREATOR = new Creator<Consignor>() {


            @SuppressWarnings({
                    "unchecked"
            })
            public Consignor createFromParcel(Parcel in) {
                Consignor instance = new Consignor();
                instance.id = ((String) in.readValue((String.class.getClassLoader())));
                instance.name = ((String) in.readValue((String.class.getClassLoader())));
                instance.gstin = ((String) in.readValue((String.class.getClassLoader())));
                instance.address = ((String) in.readValue((String.class.getClassLoader())));
                instance.city = ((City) in.readValue((City.class.getClassLoader())));
                instance.state = ((State) in.readValue((State.class.getClassLoader())));
                instance.country = ((Country) in.readValue((Country.class.getClassLoader())));
                instance.pinCode = ((String) in.readValue((String.class.getClassLoader())));
                instance.mobileNumber = ((String) in.readValue((String.class.getClassLoader())));
                instance.customerId = ((String) in.readValue((String.class.getClassLoader())));
                return instance;
            }

            public Consignor[] newArray(int size) {
                return (new Consignor[size]);
            }

        }
                ;

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

        public String getGstin() {
            return gstin;
        }

        public void setGstin(String gstin) {
            this.gstin = gstin;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public City getCity() {
            return city;
        }

        public void setCity(City city) {
            this.city = city;
        }

        public State getState() {
            return state;
        }

        public void setState(State state) {
            this.state = state;
        }

        public Country getCountry() {
            return country;
        }

        public void setCountry(Country country) {
            this.country = country;
        }

        public String getPinCode() {
            return pinCode;
        }

        public void setPinCode(String pinCode) {
            this.pinCode = pinCode;
        }

        public String getMobileNumber() {
            return mobileNumber;
        }

        public void setMobileNumber(String mobileNumber) {
            this.mobileNumber = mobileNumber;
        }

        public String getCustomerId() {
            return customerId;
        }

        public void setCustomerId(String customerId) {
            this.customerId = customerId;
        }

        public void writeToParcel(Parcel dest, int flags) {
            dest.writeValue(id);
            dest.writeValue(name);
            dest.writeValue(gstin);
            dest.writeValue(address);
            dest.writeValue(city);
            dest.writeValue(state);
            dest.writeValue(country);
            dest.writeValue(pinCode);
            dest.writeValue(mobileNumber);
            dest.writeValue(customerId);
        }

        public int describeContents() {
            return 0;
        }

    }



    public  static class Country implements Parcelable
    {

        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("name")
        @Expose
        private String name;
        public final static Parcelable.Creator<Country> CREATOR = new Creator<Country>() {


            @SuppressWarnings({
                    "unchecked"
            })
            public Country createFromParcel(Parcel in) {
                Country instance = new Country();
                instance.id = ((String) in.readValue((String.class.getClassLoader())));
                instance.name = ((String) in.readValue((String.class.getClassLoader())));
                return instance;
            }

            public Country[] newArray(int size) {
                return (new Country[size]);
            }

        }
                ;

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

        public void writeToParcel(Parcel dest, int flags) {
            dest.writeValue(id);
            dest.writeValue(name);
        }

        public int describeContents() {
            return 0;
        }

    }




    public  static class Country_ implements Parcelable
    {

        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("name")
        @Expose
        private String name;
        public final static Parcelable.Creator<Country_> CREATOR = new Creator<Country_>() {


            @SuppressWarnings({
                    "unchecked"
            })
            public Country_ createFromParcel(Parcel in) {
                Country_ instance = new Country_();
                instance.id = ((String) in.readValue((String.class.getClassLoader())));
                instance.name = ((String) in.readValue((String.class.getClassLoader())));
                return instance;
            }

            public Country_[] newArray(int size) {
                return (new Country_[size]);
            }

        }
                ;

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

        public void writeToParcel(Parcel dest, int flags) {
            dest.writeValue(id);
            dest.writeValue(name);
        }

        public int describeContents() {
            return 0;
        }

    }




    public  static class CreatedBy implements Parcelable
    {

        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("email")
        @Expose
        private String email;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("firstName")
        @Expose
        private String firstName;
        @SerializedName("lastName")
        @Expose
        private String lastName;
        public final static Parcelable.Creator<CreatedBy> CREATOR = new Creator<CreatedBy>() {


            @SuppressWarnings({
                    "unchecked"
            })
            public CreatedBy createFromParcel(Parcel in) {
                CreatedBy instance = new CreatedBy();
                instance.id = ((String) in.readValue((String.class.getClassLoader())));
                instance.email = ((String) in.readValue((String.class.getClassLoader())));
                instance.name = ((String) in.readValue((String.class.getClassLoader())));
                instance.firstName = ((String) in.readValue((String.class.getClassLoader())));
                instance.lastName = ((String) in.readValue((String.class.getClassLoader())));
                return instance;
            }

            public CreatedBy[] newArray(int size) {
                return (new CreatedBy[size]);
            }

        }
                ;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public void writeToParcel(Parcel dest, int flags) {
            dest.writeValue(id);
            dest.writeValue(email);
            dest.writeValue(name);
            dest.writeValue(firstName);
            dest.writeValue(lastName);
        }

        public int describeContents() {
            return 0;
        }

    }

    
    public  static class CreatedBy_ implements Parcelable
    {

        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("email")
        @Expose
        private String email;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("firstName")
        @Expose
        private String firstName;
        @SerializedName("lastName")
        @Expose
        private String lastName;
        public final static Parcelable.Creator<CreatedBy_> CREATOR = new Creator<CreatedBy_>() {


            @SuppressWarnings({
                    "unchecked"
            })
            public CreatedBy_ createFromParcel(Parcel in) {
                CreatedBy_ instance = new CreatedBy_();
                instance.id = ((String) in.readValue((String.class.getClassLoader())));
                instance.email = ((String) in.readValue((String.class.getClassLoader())));
                instance.name = ((String) in.readValue((String.class.getClassLoader())));
                instance.firstName = ((String) in.readValue((String.class.getClassLoader())));
                instance.lastName = ((String) in.readValue((String.class.getClassLoader())));
                return instance;
            }

            public CreatedBy_[] newArray(int size) {
                return (new CreatedBy_[size]);
            }

        }
                ;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public void writeToParcel(Parcel dest, int flags) {
            dest.writeValue(id);
            dest.writeValue(email);
            dest.writeValue(name);
            dest.writeValue(firstName);
            dest.writeValue(lastName);
        }

        public int describeContents() {
            return 0;
        }

    }




    public  static class DefaultBranch implements Parcelable
    {

        @SerializedName("id")
        @Expose
        private String id;
        public final static Parcelable.Creator<DefaultBranch> CREATOR = new Creator<DefaultBranch>() {


            @SuppressWarnings({
                    "unchecked"
            })
            public DefaultBranch createFromParcel(Parcel in) {
                DefaultBranch instance = new DefaultBranch();
                instance.id = ((String) in.readValue((String.class.getClassLoader())));
                return instance;
            }

            public DefaultBranch[] newArray(int size) {
                return (new DefaultBranch[size]);
            }

        }
                ;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public void writeToParcel(Parcel dest, int flags) {
            dest.writeValue(id);
        }

        public int describeContents() {
            return 0;
        }

    }




    public  static class Invoice implements Parcelable
    {

        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("invoiceNumber")
        @Expose
        private String invoiceNumber;
        @SerializedName("invoiceValue")
        @Expose
        private Integer invoiceValue;
        public final static Parcelable.Creator<Invoice> CREATOR = new Creator<Invoice>() {


            @SuppressWarnings({
                    "unchecked"
            })
            public Invoice createFromParcel(Parcel in) {
                Invoice instance = new Invoice();
                instance.id = ((String) in.readValue((String.class.getClassLoader())));
                instance.invoiceNumber = ((String) in.readValue((String.class.getClassLoader())));
                instance.invoiceValue = ((Integer) in.readValue((Integer.class.getClassLoader())));
                return instance;
            }

            public Invoice[] newArray(int size) {
                return (new Invoice[size]);
            }

        }
                ;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getInvoiceNumber() {
            return invoiceNumber;
        }

        public void setInvoiceNumber(String invoiceNumber) {
            this.invoiceNumber = invoiceNumber;
        }

        public Integer getInvoiceValue() {
            return invoiceValue;
        }

        public void setInvoiceValue(Integer invoiceValue) {
            this.invoiceValue = invoiceValue;
        }

        public void writeToParcel(Parcel dest, int flags) {
            dest.writeValue(id);
            dest.writeValue(invoiceNumber);
            dest.writeValue(invoiceValue);
        }

        public int describeContents() {
            return 0;
        }

    }



    public  static class Issue implements Parcelable
    {

        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("issueType")
        @Expose
        private String issueType;
        @SerializedName("issueSourceType")
        @Expose
        private String issueSourceType;
        @SerializedName("description")
        @Expose
        private String description;
        @SerializedName("createdDate")
        @Expose
        private String createdDate;
        @SerializedName("resolvedDate")
        @Expose
        private String resolvedDate;
        @SerializedName("closedDate")
        @Expose
        private String closedDate;
        @SerializedName("createdBy")
        @Expose
        private CreatedBy_ createdBy;
        @SerializedName("resolvedBy")
        @Expose
        private ResolvedBy resolvedBy;
        @SerializedName("closedBy")
        @Expose
        private ClosedBy closedBy;
        @SerializedName("issueNumber")
        @Expose
        private String issueNumber;
        @SerializedName("branch")
        @Expose
        private Branch_ branch;
        @SerializedName("issueStatus")
        @Expose
        private String issueStatus;
        @SerializedName("issueItemCount")
        @Expose
        private Integer issueItemCount;
        @SerializedName("bookedItem")
        @Expose
        private BookedItem bookedItem;
        public final static Parcelable.Creator<Issue> CREATOR = new Creator<Issue>() {


            @SuppressWarnings({
                    "unchecked"
            })
            public Issue createFromParcel(Parcel in) {
                Issue instance = new Issue();
                instance.id = ((String) in.readValue((String.class.getClassLoader())));
                instance.issueType = ((String) in.readValue((String.class.getClassLoader())));
                instance.issueSourceType = ((String) in.readValue((String.class.getClassLoader())));
                instance.description = ((String) in.readValue((String.class.getClassLoader())));
                instance.createdDate = ((String) in.readValue((String.class.getClassLoader())));
                instance.resolvedDate = ((String) in.readValue((String.class.getClassLoader())));
                instance.closedDate = ((String) in.readValue((String.class.getClassLoader())));
                instance.createdBy = ((CreatedBy_) in.readValue((CreatedBy_.class.getClassLoader())));
                instance.resolvedBy = ((ResolvedBy) in.readValue((ResolvedBy.class.getClassLoader())));
                instance.closedBy = ((ClosedBy) in.readValue((ClosedBy.class.getClassLoader())));
                instance.issueNumber = ((String) in.readValue((String.class.getClassLoader())));
                instance.branch = ((Branch_) in.readValue((Branch_.class.getClassLoader())));
                instance.issueStatus = ((String) in.readValue((String.class.getClassLoader())));
                instance.issueItemCount = ((Integer) in.readValue((Integer.class.getClassLoader())));
                instance.bookedItem = ((BookedItem) in.readValue((BookedItem.class.getClassLoader())));
                return instance;
            }

            public Issue[] newArray(int size) {
                return (new Issue[size]);
            }

        }
                ;

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

        public String getCreatedDate() {
            return createdDate;
        }

        public void setCreatedDate(String createdDate) {
            this.createdDate = createdDate;
        }

        public String getResolvedDate() {
            return resolvedDate;
        }

        public void setResolvedDate(String resolvedDate) {
            this.resolvedDate = resolvedDate;
        }

        public String getClosedDate() {
            return closedDate;
        }

        public void setClosedDate(String closedDate) {
            this.closedDate = closedDate;
        }

        public CreatedBy_ getCreatedBy() {
            return createdBy;
        }

        public void setCreatedBy(CreatedBy_ createdBy) {
            this.createdBy = createdBy;
        }

        public ResolvedBy getResolvedBy() {
            return resolvedBy;
        }

        public void setResolvedBy(ResolvedBy resolvedBy) {
            this.resolvedBy = resolvedBy;
        }

        public ClosedBy getClosedBy() {
            return closedBy;
        }

        public void setClosedBy(ClosedBy closedBy) {
            this.closedBy = closedBy;
        }

        public String getIssueNumber() {
            return issueNumber;
        }

        public void setIssueNumber(String issueNumber) {
            this.issueNumber = issueNumber;
        }

        public Branch_ getBranch() {
            return branch;
        }

        public void setBranch(Branch_ branch) {
            this.branch = branch;
        }

        public String getIssueStatus() {
            return issueStatus;
        }

        public void setIssueStatus(String issueStatus) {
            this.issueStatus = issueStatus;
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

        public void writeToParcel(Parcel dest, int flags) {
            dest.writeValue(id);
            dest.writeValue(issueType);
            dest.writeValue(issueSourceType);
            dest.writeValue(description);
            dest.writeValue(createdDate);
            dest.writeValue(resolvedDate);
            dest.writeValue(closedDate);
            dest.writeValue(createdBy);
            dest.writeValue(resolvedBy);
            dest.writeValue(closedBy);
            dest.writeValue(issueNumber);
            dest.writeValue(branch);
            dest.writeValue(issueStatus);
            dest.writeValue(issueItemCount);
            dest.writeValue(bookedItem);
        }

        public int describeContents() {
            return 0;
        }

    }




    public  static class Item implements Parcelable
    {

        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("articleType")
        @Expose
        private ArticleType articleType;
        @SerializedName("seqNo")
        @Expose
        private Integer seqNo;
        @SerializedName("itemNumber")
        @Expose
        private String itemNumber;
        @SerializedName("commodityName")
        @Expose
        private String commodityName;
        @SerializedName("sizeUOM")
        @Expose
        private String sizeUOM;
        @SerializedName("description")
        @Expose
        private String description;
        @SerializedName("length")
        @Expose
        private Float length;
        @SerializedName("width")
        @Expose
        private Float width;
        @SerializedName("height")
        @Expose
        private Float height;
        @SerializedName("cftVolume")
        @Expose
        private Float cftVolume;
        @SerializedName("cftWeight")
        @Expose
        private Float cftWeight;
        @SerializedName("actualWeight")
        @Expose
        private Float actualWeight;
        @SerializedName("chargedWeight")
        @Expose
        private Float chargedWeight;
        @SerializedName("loadedWeight")
        @Expose
        private Float loadedWeight;
        @SerializedName("unloadedWeight")
        @Expose
        private Float unloadedWeight;
        @SerializedName("unitCharge")
        @Expose
        private Float unitCharge;
        @SerializedName("totalCharge")
        @Expose
        private Float totalCharge;
        @SerializedName("loadedCharge")
        @Expose
        private Float loadedCharge;
        @SerializedName("unloadedCharge")
        @Expose
        private Float unloadedCharge;
        @SerializedName("itemCount")
        @Expose
        private Integer itemCount;
        @SerializedName("loadedCount")
        @Expose
        private Integer loadedCount;
        @SerializedName("unloadedCount")
        @Expose
        private Integer unloadedCount;
        @SerializedName("sourceBranch")
        @Expose
        private String sourceBranch;
        @SerializedName("destinationBranch")
        @Expose
        private String destinationBranch;
        @SerializedName("itemInstances")
        @Expose
        private List<ItemInstance> itemInstances = null;
        @SerializedName("vehicleStock")
        @Expose
        private List<VehicleStock> vehicleStock = null;
        @SerializedName("currentBranchStockCount")
        @Expose
        private Integer currentBranchStockCount;
        @SerializedName("otherBranchStock")
        @Expose
        private List<OtherBranchStock> otherBranchStock = null;
        @SerializedName("issues")
        @Expose
        private List<Issue> issues = null;
        public final static Parcelable.Creator<Item> CREATOR = new Creator<Item>() {


            @SuppressWarnings({
                    "unchecked"
            })
            public Item createFromParcel(Parcel in) {
                Item instance = new Item();
                instance.id = ((String) in.readValue((String.class.getClassLoader())));
                instance.articleType = ((ArticleType) in.readValue((ArticleType.class.getClassLoader())));
                instance.seqNo = ((Integer) in.readValue((Integer.class.getClassLoader())));
                instance.itemNumber = ((String) in.readValue((String.class.getClassLoader())));
                instance.commodityName = ((String) in.readValue((String.class.getClassLoader())));
                instance.sizeUOM = ((String) in.readValue((String.class.getClassLoader())));
                instance.description = ((String) in.readValue((String.class.getClassLoader())));
                instance.length = ((Float) in.readValue((Float.class.getClassLoader())));
                instance.width = ((Float) in.readValue((Float.class.getClassLoader())));
                instance.height = ((Float) in.readValue((Float.class.getClassLoader())));
                instance.cftVolume = ((Float) in.readValue((Float.class.getClassLoader())));
                instance.cftWeight = ((Float) in.readValue((Float.class.getClassLoader())));
                instance.actualWeight = ((Float) in.readValue((Float.class.getClassLoader())));
                instance.chargedWeight = ((Float) in.readValue((Float.class.getClassLoader())));
                instance.loadedWeight = ((Float) in.readValue((Float.class.getClassLoader())));
                instance.unloadedWeight = ((Float) in.readValue((Float.class.getClassLoader())));
                instance.unitCharge = ((Float) in.readValue((Float.class.getClassLoader())));
                instance.totalCharge = ((Float) in.readValue((Float.class.getClassLoader())));
                instance.loadedCharge = ((Float) in.readValue((Float.class.getClassLoader())));
                instance.unloadedCharge = ((Float) in.readValue((Float.class.getClassLoader())));
                instance.itemCount = ((Integer) in.readValue((Integer.class.getClassLoader())));
                instance.loadedCount = ((Integer) in.readValue((Integer.class.getClassLoader())));
                instance.unloadedCount = ((Integer) in.readValue((Integer.class.getClassLoader())));
                instance.sourceBranch = ((String) in.readValue((String.class.getClassLoader())));
                instance.destinationBranch = ((String) in.readValue((String.class.getClassLoader())));
                in.readList(instance.itemInstances, (ItemInstance.class.getClassLoader()));
                in.readList(instance.vehicleStock, (VehicleStock.class.getClassLoader()));
                instance.currentBranchStockCount = ((Integer) in.readValue((Integer.class.getClassLoader())));
                in.readList(instance.otherBranchStock, (OtherBranchStock.class.getClassLoader()));
                in.readList(instance.issues, (Issue.class.getClassLoader()));
                return instance;
            }

            public Item[] newArray(int size) {
                return (new Item[size]);
            }

        }
                ;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public ArticleType getArticleType() {
            return articleType;
        }

        public void setArticleType(ArticleType articleType) {
            this.articleType = articleType;
        }

        public Integer getSeqNo() {
            return seqNo;
        }

        public void setSeqNo(Integer seqNo) {
            this.seqNo = seqNo;
        }

        public String getItemNumber() {
            return itemNumber;
        }

        public void setItemNumber(String itemNumber) {
            this.itemNumber = itemNumber;
        }

        public String getCommodityName() {
            return commodityName;
        }

        public void setCommodityName(String commodityName) {
            this.commodityName = commodityName;
        }

        public String getSizeUOM() {
            return sizeUOM;
        }

        public void setSizeUOM(String sizeUOM) {
            this.sizeUOM = sizeUOM;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public Float getLength() {
            return length;
        }

        public void setLength(Float length) {
            this.length = length;
        }

        public Float getWidth() {
            return width;
        }

        public void setWidth(Float width) {
            this.width = width;
        }

        public Float getHeight() {
            return height;
        }

        public void setHeight(Float height) {
            this.height = height;
        }

        public Float getCftVolume() {
            return cftVolume;
        }

        public void setCftVolume(Float cftVolume) {
            this.cftVolume = cftVolume;
        }

        public Float getCftWeight() {
            return cftWeight;
        }

        public void setCftWeight(Float cftWeight) {
            this.cftWeight = cftWeight;
        }

        public Float getActualWeight() {
            return actualWeight;
        }

        public void setActualWeight(Float actualWeight) {
            this.actualWeight = actualWeight;
        }

        public Float getChargedWeight() {
            return chargedWeight;
        }

        public void setChargedWeight(Float chargedWeight) {
            this.chargedWeight = chargedWeight;
        }

        public Float getLoadedWeight() {
            return loadedWeight;
        }

        public void setLoadedWeight(Float loadedWeight) {
            this.loadedWeight = loadedWeight;
        }

        public Float getUnloadedWeight() {
            return unloadedWeight;
        }

        public void setUnloadedWeight(Float unloadedWeight) {
            this.unloadedWeight = unloadedWeight;
        }

        public Float getUnitCharge() {
            return unitCharge;
        }

        public void setUnitCharge(Float unitCharge) {
            this.unitCharge = unitCharge;
        }

        public Float getTotalCharge() {
            return totalCharge;
        }

        public void setTotalCharge(Float totalCharge) {
            this.totalCharge = totalCharge;
        }

        public Float getLoadedCharge() {
            return loadedCharge;
        }

        public void setLoadedCharge(Float loadedCharge) {
            this.loadedCharge = loadedCharge;
        }

        public Float getUnloadedCharge() {
            return unloadedCharge;
        }

        public void setUnloadedCharge(Float unloadedCharge) {
            this.unloadedCharge = unloadedCharge;
        }

        public Integer getItemCount() {
            return itemCount;
        }

        public void setItemCount(Integer itemCount) {
            this.itemCount = itemCount;
        }

        public Integer getLoadedCount() {
            return loadedCount;
        }

        public void setLoadedCount(Integer loadedCount) {
            this.loadedCount = loadedCount;
        }

        public Integer getUnloadedCount() {
            return unloadedCount;
        }

        public void setUnloadedCount(Integer unloadedCount) {
            this.unloadedCount = unloadedCount;
        }

        public String getSourceBranch() {
            return sourceBranch;
        }

        public void setSourceBranch(String sourceBranch) {
            this.sourceBranch = sourceBranch;
        }

        public String getDestinationBranch() {
            return destinationBranch;
        }

        public void setDestinationBranch(String destinationBranch) {
            this.destinationBranch = destinationBranch;
        }

        public List<ItemInstance> getItemInstances() {
            return itemInstances;
        }

        public void setItemInstances(List<ItemInstance> itemInstances) {
            this.itemInstances = itemInstances;
        }

        public List<VehicleStock> getVehicleStock() {
            return vehicleStock;
        }

        public void setVehicleStock(List<VehicleStock> vehicleStock) {
            this.vehicleStock = vehicleStock;
        }

        public Integer getCurrentBranchStockCount() {
            return currentBranchStockCount;
        }

        public void setCurrentBranchStockCount(Integer currentBranchStockCount) {
            this.currentBranchStockCount = currentBranchStockCount;
        }

        public List<OtherBranchStock> getOtherBranchStock() {
            return otherBranchStock;
        }

        public void setOtherBranchStock(List<OtherBranchStock> otherBranchStock) {
            this.otherBranchStock = otherBranchStock;
        }

        public List<Issue> getIssues() {
            if(issues==null){
                return new LinkedList<>();
            }
            return issues;
        }

        public void setIssues(List<Issue> issues) {
            this.issues = issues;
        }

        public void writeToParcel(Parcel dest, int flags) {
            dest.writeValue(id);
            dest.writeValue(articleType);
            dest.writeValue(seqNo);
            dest.writeValue(itemNumber);
            dest.writeValue(commodityName);
            dest.writeValue(sizeUOM);
            dest.writeValue(description);
            dest.writeValue(length);
            dest.writeValue(width);
            dest.writeValue(height);
            dest.writeValue(cftVolume);
            dest.writeValue(cftWeight);
            dest.writeValue(actualWeight);
            dest.writeValue(chargedWeight);
            dest.writeValue(loadedWeight);
            dest.writeValue(unloadedWeight);
            dest.writeValue(unitCharge);
            dest.writeValue(totalCharge);
            dest.writeValue(loadedCharge);
            dest.writeValue(unloadedCharge);
            dest.writeValue(itemCount);
            dest.writeValue(loadedCount);
            dest.writeValue(unloadedCount);
            dest.writeValue(sourceBranch);
            dest.writeValue(destinationBranch);
            dest.writeList(itemInstances);
            dest.writeList(vehicleStock);
            dest.writeValue(currentBranchStockCount);
            dest.writeList(otherBranchStock);
            dest.writeList(issues);
        }

        public int describeContents() {
            return 0;
        }

    }




    public  static class ItemInstance implements Parcelable
    {

        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("instanceNumber")
        @Expose
        private String instanceNumber;
        public final static Parcelable.Creator<ItemInstance> CREATOR = new Creator<ItemInstance>() {


            @SuppressWarnings({
                    "unchecked"
            })
            public ItemInstance createFromParcel(Parcel in) {
                ItemInstance instance = new ItemInstance();
                instance.id = ((String) in.readValue((String.class.getClassLoader())));
                instance.instanceNumber = ((String) in.readValue((String.class.getClassLoader())));
                return instance;
            }

            public ItemInstance[] newArray(int size) {
                return (new ItemInstance[size]);
            }

        }
                ;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getInstanceNumber() {
            return instanceNumber;
        }

        public void setInstanceNumber(String instanceNumber) {
            this.instanceNumber = instanceNumber;
        }

        public void writeToParcel(Parcel dest, int flags) {
            dest.writeValue(id);
            dest.writeValue(instanceNumber);
        }

        public int describeContents() {
            return 0;
        }

    }




    public  static class OtherBranchStock implements Parcelable
    {

        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("branch")
        @Expose
        private Branch branch;
        @SerializedName("bookedItemInstance")
        @Expose
        private BookedItemInstance_ bookedItemInstance;
        @SerializedName("count")
        @Expose
        private Integer count;
        @SerializedName("eventType")
        @Expose
        private String eventType;
        @SerializedName("createdDate")
        @Expose
        private String createdDate;
        public final static Parcelable.Creator<OtherBranchStock> CREATOR = new Creator<OtherBranchStock>() {


            @SuppressWarnings({
                    "unchecked"
            })
            public OtherBranchStock createFromParcel(Parcel in) {
                OtherBranchStock instance = new OtherBranchStock();
                instance.id = ((String) in.readValue((String.class.getClassLoader())));
                instance.branch = ((Branch) in.readValue((Branch.class.getClassLoader())));
                instance.bookedItemInstance = ((BookedItemInstance_) in.readValue((BookedItemInstance_.class.getClassLoader())));
                instance.count = ((Integer) in.readValue((Integer.class.getClassLoader())));
                instance.eventType = ((String) in.readValue((String.class.getClassLoader())));
                instance.createdDate = ((String) in.readValue((String.class.getClassLoader())));
                return instance;
            }

            public OtherBranchStock[] newArray(int size) {
                return (new OtherBranchStock[size]);
            }

        }
                ;

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

        public BookedItemInstance_ getBookedItemInstance() {
            return bookedItemInstance;
        }

        public void setBookedItemInstance(BookedItemInstance_ bookedItemInstance) {
            this.bookedItemInstance = bookedItemInstance;
        }

        public Integer getCount() {
            return count;
        }

        public void setCount(Integer count) {
            this.count = count;
        }

        public String getEventType() {
            return eventType;
        }

        public void setEventType(String eventType) {
            this.eventType = eventType;
        }

        public String getCreatedDate() {
            return createdDate;
        }

        public void setCreatedDate(String createdDate) {
            this.createdDate = createdDate;
        }

        public void writeToParcel(Parcel dest, int flags) {
            dest.writeValue(id);
            dest.writeValue(branch);
            dest.writeValue(bookedItemInstance);
            dest.writeValue(count);
            dest.writeValue(eventType);
            dest.writeValue(createdDate);
        }

        public int describeContents() {
            return 0;
        }

    }




    public  static class PrincipalCustomer implements Parcelable
    {

        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("customerType")
        @Expose
        private String customerType;
        @SerializedName("customerCode")
        @Expose
        private String customerCode;
        @SerializedName("customerDesignation")
        @Expose
        private String customerDesignation;
        @SerializedName("address")
        @Expose
        private String address;
        @SerializedName("emailID")
        @Expose
        private String emailID;
        @SerializedName("mobileNumber")
        @Expose
        private String mobileNumber;
        @SerializedName("contactPerson")
        @Expose
        private String contactPerson;
        @SerializedName("bookingOffice")
        @Expose
        private BookingOffice bookingOffice;
        @SerializedName("customerApprovalRefNumber")
        @Expose
        private String customerApprovalRefNumber;
        @SerializedName("gstin")
        @Expose
        private String gstin;
        @SerializedName("serviceTaxExempted")
        @Expose
        private Boolean serviceTaxExempted;
        @SerializedName("verified")
        @Expose
        private Boolean verified;
        @SerializedName("status")
        @Expose
        private Boolean status;
        @SerializedName("inward")
        @Expose
        private Boolean inward;
        @SerializedName("outward")
        @Expose
        private Boolean outward;
        public final static Parcelable.Creator<PrincipalCustomer> CREATOR = new Creator<PrincipalCustomer>() {


            @SuppressWarnings({
                    "unchecked"
            })
            public PrincipalCustomer createFromParcel(Parcel in) {
                PrincipalCustomer instance = new PrincipalCustomer();
                instance.id = ((String) in.readValue((String.class.getClassLoader())));
                instance.name = ((String) in.readValue((String.class.getClassLoader())));
                instance.customerType = ((String) in.readValue((String.class.getClassLoader())));
                instance.customerCode = ((String) in.readValue((String.class.getClassLoader())));
                instance.customerDesignation = ((String) in.readValue((String.class.getClassLoader())));
                instance.address = ((String) in.readValue((String.class.getClassLoader())));
                instance.emailID = ((String) in.readValue((String.class.getClassLoader())));
                instance.mobileNumber = ((String) in.readValue((String.class.getClassLoader())));
                instance.contactPerson = ((String) in.readValue((String.class.getClassLoader())));
                instance.bookingOffice = ((BookingOffice) in.readValue((BookingOffice.class.getClassLoader())));
                instance.customerApprovalRefNumber = ((String) in.readValue((String.class.getClassLoader())));
                instance.gstin = ((String) in.readValue((String.class.getClassLoader())));
                instance.serviceTaxExempted = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
                instance.verified = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
                instance.status = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
                instance.inward = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
                instance.outward = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
                return instance;
            }

            public PrincipalCustomer[] newArray(int size) {
                return (new PrincipalCustomer[size]);
            }

        }
                ;

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

        public String getCustomerType() {
            return customerType;
        }

        public void setCustomerType(String customerType) {
            this.customerType = customerType;
        }

        public String getCustomerCode() {
            return customerCode;
        }

        public void setCustomerCode(String customerCode) {
            this.customerCode = customerCode;
        }

        public String getCustomerDesignation() {
            return customerDesignation;
        }

        public void setCustomerDesignation(String customerDesignation) {
            this.customerDesignation = customerDesignation;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getEmailID() {
            return emailID;
        }

        public void setEmailID(String emailID) {
            this.emailID = emailID;
        }

        public String getMobileNumber() {
            return mobileNumber;
        }

        public void setMobileNumber(String mobileNumber) {
            this.mobileNumber = mobileNumber;
        }

        public String getContactPerson() {
            return contactPerson;
        }

        public void setContactPerson(String contactPerson) {
            this.contactPerson = contactPerson;
        }

        public BookingOffice getBookingOffice() {
            return bookingOffice;
        }

        public void setBookingOffice(BookingOffice bookingOffice) {
            this.bookingOffice = bookingOffice;
        }

        public String getCustomerApprovalRefNumber() {
            return customerApprovalRefNumber;
        }

        public void setCustomerApprovalRefNumber(String customerApprovalRefNumber) {
            this.customerApprovalRefNumber = customerApprovalRefNumber;
        }

        public String getGstin() {
            return gstin;
        }

        public void setGstin(String gstin) {
            this.gstin = gstin;
        }

        public Boolean getServiceTaxExempted() {
            return serviceTaxExempted;
        }

        public void setServiceTaxExempted(Boolean serviceTaxExempted) {
            this.serviceTaxExempted = serviceTaxExempted;
        }

        public Boolean getVerified() {
            return verified;
        }

        public void setVerified(Boolean verified) {
            this.verified = verified;
        }

        public Boolean getStatus() {
            return status;
        }

        public void setStatus(Boolean status) {
            this.status = status;
        }

        public Boolean getInward() {
            return inward;
        }

        public void setInward(Boolean inward) {
            this.inward = inward;
        }

        public Boolean getOutward() {
            return outward;
        }

        public void setOutward(Boolean outward) {
            this.outward = outward;
        }

        public void writeToParcel(Parcel dest, int flags) {
            dest.writeValue(id);
            dest.writeValue(name);
            dest.writeValue(customerType);
            dest.writeValue(customerCode);
            dest.writeValue(customerDesignation);
            dest.writeValue(address);
            dest.writeValue(emailID);
            dest.writeValue(mobileNumber);
            dest.writeValue(contactPerson);
            dest.writeValue(bookingOffice);
            dest.writeValue(customerApprovalRefNumber);
            dest.writeValue(gstin);
            dest.writeValue(serviceTaxExempted);
            dest.writeValue(verified);
            dest.writeValue(status);
            dest.writeValue(inward);
            dest.writeValue(outward);
        }

        public int describeContents() {
            return 0;
        }

    }






    



    public  static class PriorityApprovedBy implements Parcelable
    {

        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("email")
        @Expose
        private String email;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("firstName")
        @Expose
        private String firstName;
        @SerializedName("lastName")
        @Expose
        private String lastName;
        public final static Parcelable.Creator<PriorityApprovedBy> CREATOR = new Creator<PriorityApprovedBy>() {


            @SuppressWarnings({
                    "unchecked"
            })
            public PriorityApprovedBy createFromParcel(Parcel in) {
                PriorityApprovedBy instance = new PriorityApprovedBy();
                instance.id = ((String) in.readValue((String.class.getClassLoader())));
                instance.email = ((String) in.readValue((String.class.getClassLoader())));
                instance.name = ((String) in.readValue((String.class.getClassLoader())));
                instance.firstName = ((String) in.readValue((String.class.getClassLoader())));
                instance.lastName = ((String) in.readValue((String.class.getClassLoader())));
                return instance;
            }

            public PriorityApprovedBy[] newArray(int size) {
                return (new PriorityApprovedBy[size]);
            }

        }
                ;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public void writeToParcel(Parcel dest, int flags) {
            dest.writeValue(id);
            dest.writeValue(email);
            dest.writeValue(name);
            dest.writeValue(firstName);
            dest.writeValue(lastName);
        }

        public int describeContents() {
            return 0;
        }

    }









    public  static class Region implements Parcelable
    {

        @SerializedName("id")
        @Expose
        private String id;
        public final static Parcelable.Creator<Region> CREATOR = new Creator<Region>() {


            @SuppressWarnings({
                    "unchecked"
            })
            public Region createFromParcel(Parcel in) {
                Region instance = new Region();
                instance.id = ((String) in.readValue((String.class.getClassLoader())));
                return instance;
            }

            public Region[] newArray(int size) {
                return (new Region[size]);
            }

        }
                ;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public void writeToParcel(Parcel dest, int flags) {
            dest.writeValue(id);
        }

        public int describeContents() {
            return 0;
        }

    }





    



    public  static class ResolvedBy implements Parcelable
    {

        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("email")
        @Expose
        private String email;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("firstName")
        @Expose
        private String firstName;
        @SerializedName("lastName")
        @Expose
        private String lastName;
        public final static Parcelable.Creator<ResolvedBy> CREATOR = new Creator<ResolvedBy>() {


            @SuppressWarnings({
                    "unchecked"
            })
            public ResolvedBy createFromParcel(Parcel in) {
                ResolvedBy instance = new ResolvedBy();
                instance.id = ((String) in.readValue((String.class.getClassLoader())));
                instance.email = ((String) in.readValue((String.class.getClassLoader())));
                instance.name = ((String) in.readValue((String.class.getClassLoader())));
                instance.firstName = ((String) in.readValue((String.class.getClassLoader())));
                instance.lastName = ((String) in.readValue((String.class.getClassLoader())));
                return instance;
            }

            public ResolvedBy[] newArray(int size) {
                return (new ResolvedBy[size]);
            }

        }
                ;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public void writeToParcel(Parcel dest, int flags) {
            dest.writeValue(id);
            dest.writeValue(email);
            dest.writeValue(name);
            dest.writeValue(firstName);
            dest.writeValue(lastName);
        }

        public int describeContents() {
            return 0;
        }

    }









    public  static class ShippedFrom implements Parcelable
    {

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
        private StationId stationId;
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
        public final static Parcelable.Creator<ShippedFrom> CREATOR = new Creator<ShippedFrom>() {


            @SuppressWarnings({
                    "unchecked"
            })
            public ShippedFrom createFromParcel(Parcel in) {
                ShippedFrom instance = new ShippedFrom();
                instance.id = ((String) in.readValue((String.class.getClassLoader())));
                instance.name = ((String) in.readValue((String.class.getClassLoader())));
                instance.shortCode = ((String) in.readValue((String.class.getClassLoader())));
                instance.stationBranchCode = ((String) in.readValue((String.class.getClassLoader())));
                instance.stationId = ((StationId) in.readValue((StationId.class.getClassLoader())));
                instance.description = ((String) in.readValue((String.class.getClassLoader())));
                instance.address = ((String) in.readValue((String.class.getClassLoader())));
                instance.phoneNumber = ((String) in.readValue((String.class.getClassLoader())));
                instance.email = ((String) in.readValue((String.class.getClassLoader())));
                instance.bookingAllowed = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
                instance.deliveryAllowed = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
                instance.transhipmentAllowed = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
                instance.storageChargeAmount = ((Integer) in.readValue((Integer.class.getClassLoader())));
                instance.minConsignmentWeight = ((Integer) in.readValue((Integer.class.getClassLoader())));
                instance.bookingCancellationCharge = ((Integer) in.readValue((Integer.class.getClassLoader())));
                instance.storageNoChargeDuration = ((Integer) in.readValue((Integer.class.getClassLoader())));
                instance.minSlabForHeavyWeight = ((Integer) in.readValue((Integer.class.getClassLoader())));
                instance.heavyWeightChargePerBooking = ((Integer) in.readValue((Integer.class.getClassLoader())));
                instance.valueSurcharge = ((Integer) in.readValue((Integer.class.getClassLoader())));
                instance.valueSurchargePerAmount = ((Integer) in.readValue((Integer.class.getClassLoader())));
                instance.discountType = ((String) in.readValue((String.class.getClassLoader())));
                instance.maxBookingDiscount = ((Integer) in.readValue((Integer.class.getClassLoader())));
                instance.status = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
                return instance;
            }

            public ShippedFrom[] newArray(int size) {
                return (new ShippedFrom[size]);
            }

        }
                ;

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

        public void writeToParcel(Parcel dest, int flags) {
            dest.writeValue(id);
            dest.writeValue(name);
            dest.writeValue(shortCode);
            dest.writeValue(stationBranchCode);
            dest.writeValue(stationId);
            dest.writeValue(description);
            dest.writeValue(address);
            dest.writeValue(phoneNumber);
            dest.writeValue(email);
            dest.writeValue(bookingAllowed);
            dest.writeValue(deliveryAllowed);
            dest.writeValue(transhipmentAllowed);
            dest.writeValue(storageChargeAmount);
            dest.writeValue(minConsignmentWeight);
            dest.writeValue(bookingCancellationCharge);
            dest.writeValue(storageNoChargeDuration);
            dest.writeValue(minSlabForHeavyWeight);
            dest.writeValue(heavyWeightChargePerBooking);
            dest.writeValue(valueSurcharge);
            dest.writeValue(valueSurchargePerAmount);
            dest.writeValue(discountType);
            dest.writeValue(maxBookingDiscount);
            dest.writeValue(status);
        }

        public int describeContents() {
            return 0;
        }

    }









    public  static class ShippedTo implements Parcelable
    {

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
        private StationId_ stationId;
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
        public final static Parcelable.Creator<ShippedTo> CREATOR = new Creator<ShippedTo>() {


            @SuppressWarnings({
                    "unchecked"
            })
            public ShippedTo createFromParcel(Parcel in) {
                ShippedTo instance = new ShippedTo();
                instance.id = ((String) in.readValue((String.class.getClassLoader())));
                instance.name = ((String) in.readValue((String.class.getClassLoader())));
                instance.shortCode = ((String) in.readValue((String.class.getClassLoader())));
                instance.stationBranchCode = ((String) in.readValue((String.class.getClassLoader())));
                instance.stationId = ((StationId_) in.readValue((StationId_.class.getClassLoader())));
                instance.description = ((String) in.readValue((String.class.getClassLoader())));
                instance.address = ((String) in.readValue((String.class.getClassLoader())));
                instance.phoneNumber = ((String) in.readValue((String.class.getClassLoader())));
                instance.email = ((String) in.readValue((String.class.getClassLoader())));
                instance.bookingAllowed = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
                instance.deliveryAllowed = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
                instance.transhipmentAllowed = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
                instance.storageChargeAmount = ((Integer) in.readValue((Integer.class.getClassLoader())));
                instance.minConsignmentWeight = ((Integer) in.readValue((Integer.class.getClassLoader())));
                instance.bookingCancellationCharge = ((Integer) in.readValue((Integer.class.getClassLoader())));
                instance.storageNoChargeDuration = ((Integer) in.readValue((Integer.class.getClassLoader())));
                instance.minSlabForHeavyWeight = ((Integer) in.readValue((Integer.class.getClassLoader())));
                instance.heavyWeightChargePerBooking = ((Integer) in.readValue((Integer.class.getClassLoader())));
                instance.valueSurcharge = ((Integer) in.readValue((Integer.class.getClassLoader())));
                instance.valueSurchargePerAmount = ((Integer) in.readValue((Integer.class.getClassLoader())));
                instance.discountType = ((String) in.readValue((String.class.getClassLoader())));
                instance.maxBookingDiscount = ((Integer) in.readValue((Integer.class.getClassLoader())));
                instance.status = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
                return instance;
            }

            public ShippedTo[] newArray(int size) {
                return (new ShippedTo[size]);
            }

        }
                ;

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

        public StationId_ getStationId() {
            return stationId;
        }

        public void setStationId(StationId_ stationId) {
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

        public void writeToParcel(Parcel dest, int flags) {
            dest.writeValue(id);
            dest.writeValue(name);
            dest.writeValue(shortCode);
            dest.writeValue(stationBranchCode);
            dest.writeValue(stationId);
            dest.writeValue(description);
            dest.writeValue(address);
            dest.writeValue(phoneNumber);
            dest.writeValue(email);
            dest.writeValue(bookingAllowed);
            dest.writeValue(deliveryAllowed);
            dest.writeValue(transhipmentAllowed);
            dest.writeValue(storageChargeAmount);
            dest.writeValue(minConsignmentWeight);
            dest.writeValue(bookingCancellationCharge);
            dest.writeValue(storageNoChargeDuration);
            dest.writeValue(minSlabForHeavyWeight);
            dest.writeValue(heavyWeightChargePerBooking);
            dest.writeValue(valueSurcharge);
            dest.writeValue(valueSurchargePerAmount);
            dest.writeValue(discountType);
            dest.writeValue(maxBookingDiscount);
            dest.writeValue(status);
        }

        public int describeContents() {
            return 0;
        }

    }





    



    public  static class State implements Parcelable
    {

        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("name")
        @Expose
        private String name;
        public final static Parcelable.Creator<State> CREATOR = new Creator<State>() {


            @SuppressWarnings({
                    "unchecked"
            })
            public State createFromParcel(Parcel in) {
                State instance = new State();
                instance.id = ((String) in.readValue((String.class.getClassLoader())));
                instance.name = ((String) in.readValue((String.class.getClassLoader())));
                return instance;
            }

            public State[] newArray(int size) {
                return (new State[size]);
            }

        }
                ;

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

        public void writeToParcel(Parcel dest, int flags) {
            dest.writeValue(id);
            dest.writeValue(name);
        }

        public int describeContents() {
            return 0;
        }

    }





    



    public  static class State_ implements Parcelable
    {

        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("name")
        @Expose
        private String name;
        public final static Parcelable.Creator<State_> CREATOR = new Creator<State_>() {


            @SuppressWarnings({
                    "unchecked"
            })
            public State_ createFromParcel(Parcel in) {
                State_ instance = new State_();
                instance.id = ((String) in.readValue((String.class.getClassLoader())));
                instance.name = ((String) in.readValue((String.class.getClassLoader())));
                return instance;
            }

            public State_[] newArray(int size) {
                return (new State_[size]);
            }

        }
                ;

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

        public void writeToParcel(Parcel dest, int flags) {
            dest.writeValue(id);
            dest.writeValue(name);
        }

        public int describeContents() {
            return 0;
        }

    }









    public  static class State__ implements Parcelable
    {

        @SerializedName("id")
        @Expose
        private String id;
        public final static Parcelable.Creator<State__> CREATOR = new Creator<State__>() {


            @SuppressWarnings({
                    "unchecked"
            })
            public State__ createFromParcel(Parcel in) {
                State__ instance = new State__();
                instance.id = ((String) in.readValue((String.class.getClassLoader())));
                return instance;
            }

            public State__[] newArray(int size) {
                return (new State__[size]);
            }

        }
                ;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public void writeToParcel(Parcel dest, int flags) {
            dest.writeValue(id);
        }

        public int describeContents() {
            return 0;
        }

    }









    public  static class Station implements Parcelable
    {

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
        private State__ state;
        @SerializedName("region")
        @Expose
        private Region region;
        @SerializedName("zone")
        @Expose
        private Zone zone;
        @SerializedName("status")
        @Expose
        private Boolean status;
        public final static Parcelable.Creator<Station> CREATOR = new Creator<Station>() {


            @SuppressWarnings({
                    "unchecked"
            })
            public Station createFromParcel(Parcel in) {
                Station instance = new Station();
                instance.id = ((String) in.readValue((String.class.getClassLoader())));
                instance.name = ((String) in.readValue((String.class.getClassLoader())));
                instance.shortCode = ((String) in.readValue((String.class.getClassLoader())));
                instance.cityCharges = ((Integer) in.readValue((Integer.class.getClassLoader())));
                instance.defaultBranch = ((DefaultBranch) in.readValue((DefaultBranch.class.getClassLoader())));
                instance.transitTime = ((Integer) in.readValue((Integer.class.getClassLoader())));
                instance.area = ((Area) in.readValue((Area.class.getClassLoader())));
                instance.state = ((State__) in.readValue((State__.class.getClassLoader())));
                instance.region = ((Region) in.readValue((Region.class.getClassLoader())));
                instance.zone = ((Zone) in.readValue((Zone.class.getClassLoader())));
                instance.status = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
                return instance;
            }

            public Station[] newArray(int size) {
                return (new Station[size]);
            }

        }
                ;

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

        public State__ getState() {
            return state;
        }

        public void setState(State__ state) {
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

        public void writeToParcel(Parcel dest, int flags) {
            dest.writeValue(id);
            dest.writeValue(name);
            dest.writeValue(shortCode);
            dest.writeValue(cityCharges);
            dest.writeValue(defaultBranch);
            dest.writeValue(transitTime);
            dest.writeValue(area);
            dest.writeValue(state);
            dest.writeValue(region);
            dest.writeValue(zone);
            dest.writeValue(status);
        }

        public int describeContents() {
            return 0;
        }

    }










    public  static class StationId implements Parcelable
    {

        @SerializedName("id")
        @Expose
        private String id;
        public final static Parcelable.Creator<StationId> CREATOR = new Creator<StationId>() {


            @SuppressWarnings({
                    "unchecked"
            })
            public StationId createFromParcel(Parcel in) {
                StationId instance = new StationId();
                instance.id = ((String) in.readValue((String.class.getClassLoader())));
                return instance;
            }

            public StationId[] newArray(int size) {
                return (new StationId[size]);
            }

        }
                ;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public void writeToParcel(Parcel dest, int flags) {
            dest.writeValue(id);
        }

        public int describeContents() {
            return 0;
        }

    }


    public  static class StationId_ implements Parcelable
    {

        @SerializedName("id")
        @Expose
        private String id;
        public final static Parcelable.Creator<StationId_> CREATOR = new Creator<StationId_>() {


            @SuppressWarnings({
                    "unchecked"
            })
            public StationId_ createFromParcel(Parcel in) {
                StationId_ instance = new StationId_();
                instance.id = ((String) in.readValue((String.class.getClassLoader())));
                return instance;
            }

            public StationId_[] newArray(int size) {
                return (new StationId_[size]);
            }

        }
                ;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public void writeToParcel(Parcel dest, int flags) {
            dest.writeValue(id);
        }

        public int describeContents() {
            return 0;
        }

    }


    public  static class Vehicle implements Parcelable
    {

        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("vehicleModel")
        @Expose
        private VehicleModel vehicleModel;
        @SerializedName("vehicleNumber")
        @Expose
        private String vehicleNumber;
        @SerializedName("vehicleOwnerShipType")
        @Expose
        private String vehicleOwnerShipType;
        @SerializedName("vehicleType")
        @Expose
        private String vehicleType;
        @SerializedName("vehicleVolume")
        @Expose
        private Integer vehicleVolume;
        @SerializedName("vehiclePermissibleWeight")
        @Expose
        private Integer vehiclePermissibleWeight;
        @SerializedName("status")
        @Expose
        private Boolean status;
        public final static Parcelable.Creator<Vehicle> CREATOR = new Creator<Vehicle>() {


            @SuppressWarnings({
                    "unchecked"
            })
            public Vehicle createFromParcel(Parcel in) {
                Vehicle instance = new Vehicle();
                instance.id = ((String) in.readValue((String.class.getClassLoader())));
                instance.vehicleModel = ((VehicleModel) in.readValue((VehicleModel.class.getClassLoader())));
                instance.vehicleNumber = ((String) in.readValue((String.class.getClassLoader())));
                instance.vehicleOwnerShipType = ((String) in.readValue((String.class.getClassLoader())));
                instance.vehicleType = ((String) in.readValue((String.class.getClassLoader())));
                instance.vehicleVolume = ((Integer) in.readValue((Integer.class.getClassLoader())));
                instance.vehiclePermissibleWeight = ((Integer) in.readValue((Integer.class.getClassLoader())));
                instance.status = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
                return instance;
            }

            public Vehicle[] newArray(int size) {
                return (new Vehicle[size]);
            }

        }
                ;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

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

        public Integer getVehicleVolume() {
            return vehicleVolume;
        }

        public void setVehicleVolume(Integer vehicleVolume) {
            this.vehicleVolume = vehicleVolume;
        }

        public Integer getVehiclePermissibleWeight() {
            return vehiclePermissibleWeight;
        }

        public void setVehiclePermissibleWeight(Integer vehiclePermissibleWeight) {
            this.vehiclePermissibleWeight = vehiclePermissibleWeight;
        }

        public Boolean getStatus() {
            return status;
        }

        public void setStatus(Boolean status) {
            this.status = status;
        }

        public void writeToParcel(Parcel dest, int flags) {
            dest.writeValue(id);
            dest.writeValue(vehicleModel);
            dest.writeValue(vehicleNumber);
            dest.writeValue(vehicleOwnerShipType);
            dest.writeValue(vehicleType);
            dest.writeValue(vehicleVolume);
            dest.writeValue(vehiclePermissibleWeight);
            dest.writeValue(status);
        }

        public int describeContents() {
            return 0;
        }

    }



    public  static class VehicleModel implements Parcelable
    {

        @SerializedName("id")
        @Expose
        private String id;
        public final static Parcelable.Creator<VehicleModel> CREATOR = new Creator<VehicleModel>() {


            @SuppressWarnings({
                    "unchecked"
            })
            public VehicleModel createFromParcel(Parcel in) {
                VehicleModel instance = new VehicleModel();
                instance.id = ((String) in.readValue((String.class.getClassLoader())));
                return instance;
            }

            public VehicleModel[] newArray(int size) {
                return (new VehicleModel[size]);
            }

        }
                ;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public void writeToParcel(Parcel dest, int flags) {
            dest.writeValue(id);
        }

        public int describeContents() {
            return 0;
        }

    }



    public  static class VehicleStock implements Parcelable
    {

        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("vehicle")
        @Expose
        private Vehicle vehicle;
        @SerializedName("bookedItemInstance")
        @Expose
        private BookedItemInstance bookedItemInstance;
        @SerializedName("count")
        @Expose
        private Integer count;
        @SerializedName("eventType")
        @Expose
        private String eventType;
        @SerializedName("createdDate")
        @Expose
        private String createdDate;
        public final static Parcelable.Creator<VehicleStock> CREATOR = new Creator<VehicleStock>() {


            @SuppressWarnings({
                    "unchecked"
            })
            public VehicleStock createFromParcel(Parcel in) {
                VehicleStock instance = new VehicleStock();
                instance.id = ((String) in.readValue((String.class.getClassLoader())));
                instance.vehicle = ((Vehicle) in.readValue((Vehicle.class.getClassLoader())));
                instance.bookedItemInstance = ((BookedItemInstance) in.readValue((BookedItemInstance.class.getClassLoader())));
                instance.count = ((Integer) in.readValue((Integer.class.getClassLoader())));
                instance.eventType = ((String) in.readValue((String.class.getClassLoader())));
                instance.createdDate = ((String) in.readValue((String.class.getClassLoader())));
                return instance;
            }

            public VehicleStock[] newArray(int size) {
                return (new VehicleStock[size]);
            }

        }
                ;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public Vehicle getVehicle() {
            return vehicle;
        }

        public void setVehicle(Vehicle vehicle) {
            this.vehicle = vehicle;
        }

        public BookedItemInstance getBookedItemInstance() {
            return bookedItemInstance;
        }

        public void setBookedItemInstance(BookedItemInstance bookedItemInstance) {
            this.bookedItemInstance = bookedItemInstance;
        }

        public Integer getCount() {
            return count;
        }

        public void setCount(Integer count) {
            this.count = count;
        }

        public String getEventType() {
            return eventType;
        }

        public void setEventType(String eventType) {
            this.eventType = eventType;
        }

        public String getCreatedDate() {
            return createdDate;
        }

        public void setCreatedDate(String createdDate) {
            this.createdDate = createdDate;
        }

        public void writeToParcel(Parcel dest, int flags) {
            dest.writeValue(id);
            dest.writeValue(vehicle);
            dest.writeValue(bookedItemInstance);
            dest.writeValue(count);
            dest.writeValue(eventType);
            dest.writeValue(createdDate);
        }

        public int describeContents() {
            return 0;
        }

    }



    public  static class Zone implements Parcelable
    {

        @SerializedName("id")
        @Expose
        private String id;
        public final static Parcelable.Creator<Zone> CREATOR = new Creator<Zone>() {


            @SuppressWarnings({
                    "unchecked"
            })
            public Zone createFromParcel(Parcel in) {
                Zone instance = new Zone();
                instance.id = ((String) in.readValue((String.class.getClassLoader())));
                return instance;
            }

            public Zone[] newArray(int size) {
                return (new Zone[size]);
            }

        };

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public void writeToParcel(Parcel dest, int flags) {
            dest.writeValue(id);
        }

        public int describeContents() {
            return 0;
        }

    }



}
 
        
         

