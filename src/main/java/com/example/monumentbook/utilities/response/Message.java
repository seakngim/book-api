package com.example.monumentbook.utilities.response;

public class Message
{
    public String getSuccess(String tableName)
    {
        return  tableName + " have found successfully.";
    }

    public String getFail(String tableName)
    {
        return tableName + " not found.";
    }

    public String insertSuccess(String tableName)
    {
        return tableName + " has inserted successfully.";
    }

    public String insertFail(String tableName)
    {
        return tableName + " failed to inserted.";
    }

    public String deleteSuccess(String tableName)
    {
        return tableName + " has deleted successfully.";
    }

    public String deleteFail(String tableName)
    {
        return tableName + " failed to delete.";
    }

    public String updateSuccess(String tableName)
    {
        return tableName + " has updated successfully";
    }

    public String updateFail(String tableName)
    {
        return tableName + " failed to update.";
    }

    public String loginSuccess(){
        return  "Login Success";
    }

    public String logOutSuccess(){
        return  "Logout Successfully";
    }

    public String loginFail(){
        return "Wrong username and password, please try again.";
    }

    public String logoutFail(){
        return "Couldn't find token, please try again.";
    }

    public String uploadSuccess(){return "Upload success.";}

    public String uploadFail(){return "failed to upload.";}

    public String notAllow(){return "Unauthorized : Your role not allow to do this action!!";}

    public String alreadyExist(String tableName){return tableName+ " is already exists!!";}

    public String notExist(String tableName){return tableName+ " is not exists!!";}

    public String idNoFound(String idName){return idName+ " not found.";}

    public String changePassword() {
        return "Password has changed successfully.";
    }

    public String changePasswordFail() {
        return "Wrong old password!";
    }

    public String wrongEmail() {
        return "Wrong Email!";
    }

    public String wrongRecoveryCode() {
        return "Wrong recovery code!";
    }

    public String emailSuccess() {
        return "Email has sent successfully.";
    }

    public String emailError() {
        return "Email has sent error!";
    }

    public String dateTypeError() {
        return "Invalid date type. Please use either 'morning' or 'afternoon'.";
    }

    public String invalidRequest(String requestType) {
        return "Invalid data. Please check your " + requestType + " request.";
    }

    public String maxRequestsReached(String maxType) {
        return "Maximum " + maxType + " reached. Please try again later.";
    }

    public String approveLeaveRequest(){
        return "Leave request has been approved successfully.";
    }

    public String rejectLeaveRequest(){
        return "Leave request has been rejected successfully.";
    }

    public String userNotificationAccept(String userName){
        return userName + " Accepted Your Leave Request!";
    }

    public String userNotificationReject(String userName){
        return userName + " Rejected Your Leave Request!";
    }

    public String sendNotificationSuccess(){
        return "Send notification successfully";
    }

    public String sendNotificationFailed(){
        return "Unable to send notification!";
    }

    public String subscribeNotificationSuccess(){
        return "Subscribe notification successfully";
    }

    public String pushNotificationSuccess(){
        return "Push notification successfully";
    }

    public String accountLoginNotification(){
        return "Your account has been logged from another device!";
    }
}
