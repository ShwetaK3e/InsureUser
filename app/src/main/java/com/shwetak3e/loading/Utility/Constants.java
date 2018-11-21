package com.shwetak3e.loading.Utility;

/**
 * Created by shwetakumar on 7/27/17.
 */

public class Constants {

    //Url
    //public static final String BASE_URL="https://cargocentral.herokuapp.com/";
    public static final String BASE_URL="http://127.0.0.1:9000/";
    public static final String VEHICLE_LIST="vehicles/eligible/{branchId}";
    public static final String ROUTE_LIST="routes";
    public static final String BRANCH_LIST="branch/list/json";
    public static final String SHIPMENT_LIST="shipments/loading";
    public static final String VEHICLE="vehicles";
    public static final String BRANCH="branch";
    public static final String FINALIZE_LOADING="transientloadingsheet/finalize";
    public static final String ADD_ITEM_ISSUE="itemissues";
    public static final String ADD_VEHILCLE="vehicles";
    public static final String GET_VEHICLE_MODEL="vehiclemodels";

    //sharedPreferences
    public static final String PREF_NAME="jetty_pref";
    public static final String APP_MODE="app_mode";
    public static final String BRANCH_NAME="branch_name";
    public static final String BRANCH_ID="branch_id";



    // intent and arguments
    public  static final String SHOW_ISSUE_ON_TRUCK_DETAILS="show_issues";
    public  static final String SHIPMENT_ID="shipment_id";
    public  static final String VEHICLE_ID="vehicle_id";
    public static final String ISSUE_TYPE="issue_type";
    public static final String ISSUE="issue";
    public static final String CURRENT_VEHICLE="issue_type";
    public static final String MISSING_COUNT="missing_count";
    public static final String TOTAL_ITEM_COUNT="total_item_count";
    public static final String ACTUAL_WEIGHT="actual_weight";
    public static final String ISSUE_LIST="issue_list";
    public static final String ISSUE_LIST_SIZE="issue_list_size";

    public enum IssueType {
        DAMAGE("DAMAGE"),
        MISSING("MISSING"),
        WEIGHT("WEIGHT CHANGE");

        String issue_type;

        IssueType(String issue_type){
            this.issue_type=issue_type;
        }

        public String getIssue_type() {
            return issue_type;
        }
    }

}
