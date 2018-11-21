package com.shwetak3e.loading.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Vehicle implements Parcelable
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

    };

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

        };

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


