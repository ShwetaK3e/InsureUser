package com.shwetak3e.loading.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by shwetakumar on 8/16/17.
 */
public class AddIssueServerResponse implements Parcelable {

    @SerializedName("id")
    @Expose
    private Object id;
    @SerializedName("issueType")
    @Expose
    private String issueType;
    @SerializedName("issueSourceType")
    @Expose
    private String issueSourceType;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("createdBy")
    @Expose
    private Object createdBy;
    @SerializedName("resolvedBy")
    @Expose
    private Object resolvedBy;
    @SerializedName("closedBy")
    @Expose
    private Object closedBy;
    @SerializedName("createdDate")
    @Expose
    private Object createdDate;
    @SerializedName("resolvedDate")
    @Expose
    private Object resolvedDate;
    @SerializedName("closedDate")
    @Expose
    private Object closedDate;
    @SerializedName("issueNumber")
    @Expose
    private String issueNumber;
    @SerializedName("branch")
    @Expose
    private Branch branch;
    @SerializedName("vehicle")
    @Expose
    private Vehicle vehicle;
    @SerializedName("issueStatus")
    @Expose
    private Object issueStatus;
    @SerializedName("bookedItem")
    @Expose
    private BookedItem bookedItem;
    @SerializedName("issueItemCount")
    @Expose
    private Integer issueItemCount;
    public final static Parcelable.Creator<AddIssueServerResponse> CREATOR = new Creator<AddIssueServerResponse>() {


        @SuppressWarnings({
                "unchecked"
        })
        public AddIssueServerResponse createFromParcel(Parcel in) {
            AddIssueServerResponse instance = new AddIssueServerResponse();
            instance.id = in.readValue((Object.class.getClassLoader()));
            instance.issueType = ((String) in.readValue((String.class.getClassLoader())));
            instance.issueSourceType = ((String) in.readValue((String.class.getClassLoader())));
            instance.description = ((String) in.readValue((String.class.getClassLoader())));
            instance.createdBy = in.readValue((Object.class.getClassLoader()));
            instance.resolvedBy = in.readValue((Object.class.getClassLoader()));
            instance.closedBy = in.readValue((Object.class.getClassLoader()));
            instance.createdDate = in.readValue((Object.class.getClassLoader()));
            instance.resolvedDate = in.readValue((Object.class.getClassLoader()));
            instance.closedDate = in.readValue((Object.class.getClassLoader()));
            instance.issueNumber = ((String) in.readValue((String.class.getClassLoader())));
            instance.branch = ((Branch) in.readValue((Branch.class.getClassLoader())));
            instance.vehicle = ((Vehicle) in.readValue((Vehicle.class.getClassLoader())));
            instance.issueStatus = in.readValue((Object.class.getClassLoader()));
            instance.bookedItem = ((BookedItem) in.readValue((BookedItem.class.getClassLoader())));
            instance.issueItemCount = ((Integer) in.readValue((Integer.class.getClassLoader())));
            return instance;
        }

        public AddIssueServerResponse[] newArray(int size) {
            return (new AddIssueServerResponse[size]);
        }

    }
            ;

    public Object getId() {
        return id;
    }

    public void setId(Object id) {
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

    public Object getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Object createdBy) {
        this.createdBy = createdBy;
    }

    public Object getResolvedBy() {
        return resolvedBy;
    }

    public void setResolvedBy(Object resolvedBy) {
        this.resolvedBy = resolvedBy;
    }

    public Object getClosedBy() {
        return closedBy;
    }

    public void setClosedBy(Object closedBy) {
        this.closedBy = closedBy;
    }

    public Object getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Object createdDate) {
        this.createdDate = createdDate;
    }

    public Object getResolvedDate() {
        return resolvedDate;
    }

    public void setResolvedDate(Object resolvedDate) {
        this.resolvedDate = resolvedDate;
    }

    public Object getClosedDate() {
        return closedDate;
    }

    public void setClosedDate(Object closedDate) {
        this.closedDate = closedDate;
    }

    public String getIssueNumber() {
        return issueNumber;
    }

    public void setIssueNumber(String issueNumber) {
        this.issueNumber = issueNumber;
    }

    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public Object getIssueStatus() {
        return issueStatus;
    }

    public void setIssueStatus(Object issueStatus) {
        this.issueStatus = issueStatus;
    }

    public BookedItem getBookedItem() {
        return bookedItem;
    }

    public void setBookedItem(BookedItem bookedItem) {
        this.bookedItem = bookedItem;
    }

    public Integer getIssueItemCount() {
        return issueItemCount;
    }

    public void setIssueItemCount(Integer issueItemCount) {
        this.issueItemCount = issueItemCount;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(issueType);
        dest.writeValue(issueSourceType);
        dest.writeValue(description);
        dest.writeValue(createdBy);
        dest.writeValue(resolvedBy);
        dest.writeValue(closedBy);
        dest.writeValue(createdDate);
        dest.writeValue(resolvedDate);
        dest.writeValue(closedDate);
        dest.writeValue(issueNumber);
        dest.writeValue(branch);
        dest.writeValue(vehicle);
        dest.writeValue(issueStatus);
        dest.writeValue(bookedItem);
        dest.writeValue(issueItemCount);
    }

    public int describeContents() {
        return 0;
    }



    public static class ArticleType implements Parcelable
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
        private Double length;
        @SerializedName("width")
        @Expose
        private Double width;
        @SerializedName("height")
        @Expose
        private Double height;
        @SerializedName("sizeUOM")
        @Expose
        private String sizeUOM;
        @SerializedName("minWeight")
        @Expose
        private Integer minWeight;
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
                instance.length = ((Double) in.readValue((Double.class.getClassLoader())));
                instance.width = ((Double) in.readValue((Double.class.getClassLoader())));
                instance.height = ((Double) in.readValue((Double.class.getClassLoader())));
                instance.sizeUOM = ((String) in.readValue((String.class.getClassLoader())));
                instance.minWeight = ((Integer) in.readValue((Integer.class.getClassLoader())));
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

        public Double getLength() {
            return length;
        }

        public void setLength(Double length) {
            this.length = length;
        }

        public Double getWidth() {
            return width;
        }

        public void setWidth(Double width) {
            this.width = width;
        }

        public Double getHeight() {
            return height;
        }

        public void setHeight(Double height) {
            this.height = height;
        }

        public String getSizeUOM() {
            return sizeUOM;
        }

        public void setSizeUOM(String sizeUOM) {
            this.sizeUOM = sizeUOM;
        }

        public Integer getMinWeight() {
            return minWeight;
        }

        public void setMinWeight(Integer minWeight) {
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


    public static class BookedItem implements Parcelable
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
        private Integer length;
        @SerializedName("width")
        @Expose
        private Integer width;
        @SerializedName("height")
        @Expose
        private Integer height;
        @SerializedName("cftVolume")
        @Expose
        private Integer cftVolume;
        @SerializedName("cftWeight")
        @Expose
        private Integer cftWeight;
        @SerializedName("actualWeight")
        @Expose
        private Integer actualWeight;
        @SerializedName("chargedWeight")
        @Expose
        private Integer chargedWeight;
        @SerializedName("loadedWeight")
        @Expose
        private Integer loadedWeight;
        @SerializedName("unloadedWeight")
        @Expose
        private Integer unloadedWeight;
        @SerializedName("unitCharge")
        @Expose
        private Integer unitCharge;
        @SerializedName("totalCharge")
        @Expose
        private Integer totalCharge;
        @SerializedName("loadedCharge")
        @Expose
        private Integer loadedCharge;
        @SerializedName("unloadedCharge")
        @Expose
        private Integer unloadedCharge;
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
        private Object sourceBranch;
        @SerializedName("destinationBranch")
        @Expose
        private Object destinationBranch;
        @SerializedName("createdDate")
        @Expose
        private Object createdDate;
        @SerializedName("itemInstances")
        @Expose
        private List<ItemInstance> itemInstances = null;
        @SerializedName("vehicleStock")
        @Expose
        private Object vehicleStock;
        @SerializedName("currentBranchStockCount")
        @Expose
        private Integer currentBranchStockCount;
        @SerializedName("otherBranchStock")
        @Expose
        private Object otherBranchStock;
        @SerializedName("issues")
        @Expose
        private Object issues;
        public final static Parcelable.Creator<BookedItem> CREATOR = new Creator<BookedItem>() {


            @SuppressWarnings({
                    "unchecked"
            })
            public BookedItem createFromParcel(Parcel in) {
                BookedItem instance = new BookedItem();
                instance.id = ((String) in.readValue((String.class.getClassLoader())));
                instance.articleType = ((ArticleType) in.readValue((ArticleType.class.getClassLoader())));
                instance.seqNo = ((Integer) in.readValue((Integer.class.getClassLoader())));
                instance.itemNumber = ((String) in.readValue((String.class.getClassLoader())));
                instance.commodityName = ((String) in.readValue((String.class.getClassLoader())));
                instance.sizeUOM = ((String) in.readValue((String.class.getClassLoader())));
                instance.description = ((String) in.readValue((String.class.getClassLoader())));
                instance.length = ((Integer) in.readValue((Integer.class.getClassLoader())));
                instance.width = ((Integer) in.readValue((Integer.class.getClassLoader())));
                instance.height = ((Integer) in.readValue((Integer.class.getClassLoader())));
                instance.cftVolume = ((Integer) in.readValue((Integer.class.getClassLoader())));
                instance.cftWeight = ((Integer) in.readValue((Integer.class.getClassLoader())));
                instance.actualWeight = ((Integer) in.readValue((Integer.class.getClassLoader())));
                instance.chargedWeight = ((Integer) in.readValue((Integer.class.getClassLoader())));
                instance.loadedWeight = ((Integer) in.readValue((Integer.class.getClassLoader())));
                instance.unloadedWeight = ((Integer) in.readValue((Integer.class.getClassLoader())));
                instance.unitCharge = ((Integer) in.readValue((Integer.class.getClassLoader())));
                instance.totalCharge = ((Integer) in.readValue((Integer.class.getClassLoader())));
                instance.loadedCharge = ((Integer) in.readValue((Integer.class.getClassLoader())));
                instance.unloadedCharge = ((Integer) in.readValue((Integer.class.getClassLoader())));
                instance.itemCount = ((Integer) in.readValue((Integer.class.getClassLoader())));
                instance.loadedCount = ((Integer) in.readValue((Integer.class.getClassLoader())));
                instance.unloadedCount = ((Integer) in.readValue((Integer.class.getClassLoader())));
                instance.sourceBranch = in.readValue((Object.class.getClassLoader()));
                instance.destinationBranch = in.readValue((Object.class.getClassLoader()));
                instance.createdDate = in.readValue((Object.class.getClassLoader()));
                in.readList(instance.itemInstances, (ItemInstance.class.getClassLoader()));
                instance.vehicleStock = in.readValue((Object.class.getClassLoader()));
                instance.currentBranchStockCount = ((Integer) in.readValue((Integer.class.getClassLoader())));
                instance.otherBranchStock = in.readValue((Object.class.getClassLoader()));
                instance.issues = in.readValue((Object.class.getClassLoader()));
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

        public Integer getLength() {
            return length;
        }

        public void setLength(Integer length) {
            this.length = length;
        }

        public Integer getWidth() {
            return width;
        }

        public void setWidth(Integer width) {
            this.width = width;
        }

        public Integer getHeight() {
            return height;
        }

        public void setHeight(Integer height) {
            this.height = height;
        }

        public Integer getCftVolume() {
            return cftVolume;
        }

        public void setCftVolume(Integer cftVolume) {
            this.cftVolume = cftVolume;
        }

        public Integer getCftWeight() {
            return cftWeight;
        }

        public void setCftWeight(Integer cftWeight) {
            this.cftWeight = cftWeight;
        }

        public Integer getActualWeight() {
            return actualWeight;
        }

        public void setActualWeight(Integer actualWeight) {
            this.actualWeight = actualWeight;
        }

        public Integer getChargedWeight() {
            return chargedWeight;
        }

        public void setChargedWeight(Integer chargedWeight) {
            this.chargedWeight = chargedWeight;
        }

        public Integer getLoadedWeight() {
            return loadedWeight;
        }

        public void setLoadedWeight(Integer loadedWeight) {
            this.loadedWeight = loadedWeight;
        }

        public Integer getUnloadedWeight() {
            return unloadedWeight;
        }

        public void setUnloadedWeight(Integer unloadedWeight) {
            this.unloadedWeight = unloadedWeight;
        }

        public Integer getUnitCharge() {
            return unitCharge;
        }

        public void setUnitCharge(Integer unitCharge) {
            this.unitCharge = unitCharge;
        }

        public Integer getTotalCharge() {
            return totalCharge;
        }

        public void setTotalCharge(Integer totalCharge) {
            this.totalCharge = totalCharge;
        }

        public Integer getLoadedCharge() {
            return loadedCharge;
        }

        public void setLoadedCharge(Integer loadedCharge) {
            this.loadedCharge = loadedCharge;
        }

        public Integer getUnloadedCharge() {
            return unloadedCharge;
        }

        public void setUnloadedCharge(Integer unloadedCharge) {
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

        public Object getSourceBranch() {
            return sourceBranch;
        }

        public void setSourceBranch(Object sourceBranch) {
            this.sourceBranch = sourceBranch;
        }

        public Object getDestinationBranch() {
            return destinationBranch;
        }

        public void setDestinationBranch(Object destinationBranch) {
            this.destinationBranch = destinationBranch;
        }

        public Object getCreatedDate() {
            return createdDate;
        }

        public void setCreatedDate(Object createdDate) {
            this.createdDate = createdDate;
        }

        public List<ItemInstance> getItemInstances() {
            return itemInstances;
        }

        public void setItemInstances(List<ItemInstance> itemInstances) {
            this.itemInstances = itemInstances;
        }

        public Object getVehicleStock() {
            return vehicleStock;
        }

        public void setVehicleStock(Object vehicleStock) {
            this.vehicleStock = vehicleStock;
        }

        public Integer getCurrentBranchStockCount() {
            return currentBranchStockCount;
        }

        public void setCurrentBranchStockCount(Integer currentBranchStockCount) {
            this.currentBranchStockCount = currentBranchStockCount;
        }

        public Object getOtherBranchStock() {
            return otherBranchStock;
        }

        public void setOtherBranchStock(Object otherBranchStock) {
            this.otherBranchStock = otherBranchStock;
        }

        public Object getIssues() {
            return issues;
        }

        public void setIssues(Object issues) {
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
            dest.writeValue(createdDate);
            dest.writeList(itemInstances);
            dest.writeValue(vehicleStock);
            dest.writeValue(currentBranchStockCount);
            dest.writeValue(otherBranchStock);
            dest.writeValue(issues);
        }

        public int describeContents() {
            return 0;
        }

    }

    public static class Branch implements Parcelable
    {

        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("addressLine1")
        @Expose
        private Object addressLine1;
        @SerializedName("addressLine2")
        @Expose
        private Object addressLine2;
        @SerializedName("city")
        @Expose
        private Object city;
        @SerializedName("state")
        @Expose
        private Object state;
        @SerializedName("country")
        @Expose
        private Object country;
        @SerializedName("pinCode")
        @Expose
        private Object pinCode;
        @SerializedName("latitude")
        @Expose
        private Integer latitude;
        @SerializedName("longitude")
        @Expose
        private Integer longitude;
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
        @SerializedName("phoneNumber")
        @Expose
        private Object phoneNumber;
        @SerializedName("email")
        @Expose
        private Object email;
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
        private Object discountType;
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
                instance.addressLine1 = in.readValue((Object.class.getClassLoader()));
                instance.addressLine2 = in.readValue((Object.class.getClassLoader()));
                instance.city = in.readValue((Object.class.getClassLoader()));
                instance.state = in.readValue((Object.class.getClassLoader()));
                instance.country = in.readValue((Object.class.getClassLoader()));
                instance.pinCode = in.readValue((Object.class.getClassLoader()));
                instance.latitude = ((Integer) in.readValue((Integer.class.getClassLoader())));
                instance.longitude = ((Integer) in.readValue((Integer.class.getClassLoader())));
                instance.name = ((String) in.readValue((String.class.getClassLoader())));
                instance.shortCode = ((String) in.readValue((String.class.getClassLoader())));
                instance.stationBranchCode = ((String) in.readValue((String.class.getClassLoader())));
                instance.station = ((Station) in.readValue((Station.class.getClassLoader())));
                instance.description = ((String) in.readValue((String.class.getClassLoader())));
                instance.phoneNumber = in.readValue((Object.class.getClassLoader()));
                instance.email = in.readValue((Object.class.getClassLoader()));
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
                instance.discountType = in.readValue((Object.class.getClassLoader()));
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

        public Object getAddressLine1() {
            return addressLine1;
        }

        public void setAddressLine1(Object addressLine1) {
            this.addressLine1 = addressLine1;
        }

        public Object getAddressLine2() {
            return addressLine2;
        }

        public void setAddressLine2(Object addressLine2) {
            this.addressLine2 = addressLine2;
        }

        public Object getCity() {
            return city;
        }

        public void setCity(Object city) {
            this.city = city;
        }

        public Object getState() {
            return state;
        }

        public void setState(Object state) {
            this.state = state;
        }

        public Object getCountry() {
            return country;
        }

        public void setCountry(Object country) {
            this.country = country;
        }

        public Object getPinCode() {
            return pinCode;
        }

        public void setPinCode(Object pinCode) {
            this.pinCode = pinCode;
        }

        public Integer getLatitude() {
            return latitude;
        }

        public void setLatitude(Integer latitude) {
            this.latitude = latitude;
        }

        public Integer getLongitude() {
            return longitude;
        }

        public void setLongitude(Integer longitude) {
            this.longitude = longitude;
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

        public Object getPhoneNumber() {
            return phoneNumber;
        }

        public void setPhoneNumber(Object phoneNumber) {
            this.phoneNumber = phoneNumber;
        }

        public Object getEmail() {
            return email;
        }

        public void setEmail(Object email) {
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

        public Object getDiscountType() {
            return discountType;
        }

        public void setDiscountType(Object discountType) {
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
            dest.writeValue(addressLine1);
            dest.writeValue(addressLine2);
            dest.writeValue(city);
            dest.writeValue(state);
            dest.writeValue(country);
            dest.writeValue(pinCode);
            dest.writeValue(latitude);
            dest.writeValue(longitude);
            dest.writeValue(name);
            dest.writeValue(shortCode);
            dest.writeValue(stationBranchCode);
            dest.writeValue(station);
            dest.writeValue(description);
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

    public static class ItemInstance implements Parcelable
    {

        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("instanceNumber")
        @Expose
        private String instanceNumber;
        @SerializedName("issue")
        @Expose
        private Object issue;
        public final static Parcelable.Creator<ItemInstance> CREATOR = new Creator<ItemInstance>() {


            @SuppressWarnings({
                    "unchecked"
            })
            public ItemInstance createFromParcel(Parcel in) {
                ItemInstance instance = new ItemInstance();
                instance.id = ((String) in.readValue((String.class.getClassLoader())));
                instance.instanceNumber = ((String) in.readValue((String.class.getClassLoader())));
                instance.issue = in.readValue((Object.class.getClassLoader()));
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

        public Object getIssue() {
            return issue;
        }

        public void setIssue(Object issue) {
            this.issue = issue;
        }

        public void writeToParcel(Parcel dest, int flags) {
            dest.writeValue(id);
            dest.writeValue(instanceNumber);
            dest.writeValue(issue);
        }

        public int describeContents() {
            return 0;
        }

    }

    public static class Station implements Parcelable
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
        private Object cityCharges;
        @SerializedName("defaultBranch")
        @Expose
        private Object defaultBranch;
        @SerializedName("transitTime")
        @Expose
        private Object transitTime;
        @SerializedName("area")
        @Expose
        private Object area;
        @SerializedName("state")
        @Expose
        private Object state;
        @SerializedName("region")
        @Expose
        private Object region;
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
                instance.cityCharges = in.readValue((Object.class.getClassLoader()));
                instance.defaultBranch = in.readValue((Object.class.getClassLoader()));
                instance.transitTime = in.readValue((Object.class.getClassLoader()));
                instance.area = in.readValue((Object.class.getClassLoader()));
                instance.state = in.readValue((Object.class.getClassLoader()));
                instance.region = in.readValue((Object.class.getClassLoader()));
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

        public Object getCityCharges() {
            return cityCharges;
        }

        public void setCityCharges(Object cityCharges) {
            this.cityCharges = cityCharges;
        }

        public Object getDefaultBranch() {
            return defaultBranch;
        }

        public void setDefaultBranch(Object defaultBranch) {
            this.defaultBranch = defaultBranch;
        }

        public Object getTransitTime() {
            return transitTime;
        }

        public void setTransitTime(Object transitTime) {
            this.transitTime = transitTime;
        }

        public Object getArea() {
            return area;
        }

        public void setArea(Object area) {
            this.area = area;
        }

        public Object getState() {
            return state;
        }

        public void setState(Object state) {
            this.state = state;
        }

        public Object getRegion() {
            return region;
        }

        public void setRegion(Object region) {
            this.region = region;
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
            dest.writeValue(status);
        }

        public int describeContents() {
            return 0;
        }

    }

    public static class Vehicle implements Parcelable
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
        private Object vehicleType;
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
                instance.vehicleType = in.readValue((Object.class.getClassLoader()));
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

        public Object getVehicleType() {
            return vehicleType;
        }

        public void setVehicleType(Object vehicleType) {
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

    public static class VehicleModel implements Parcelable
    {

        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("description")
        @Expose
        private String description;
        @SerializedName("status")
        @Expose
        private Boolean status;
        public final static Parcelable.Creator<VehicleModel> CREATOR = new Creator<VehicleModel>() {


            @SuppressWarnings({
                    "unchecked"
            })
            public VehicleModel createFromParcel(Parcel in) {
                VehicleModel instance = new VehicleModel();
                instance.id = ((String) in.readValue((String.class.getClassLoader())));
                instance.name = ((String) in.readValue((String.class.getClassLoader())));
                instance.description = ((String) in.readValue((String.class.getClassLoader())));
                instance.status = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
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

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
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
            dest.writeValue(description);
            dest.writeValue(status);
        }

        public int describeContents() {
            return 0;
        }

    }



}