app.factory('NotificationService',function($http){
	var notificationService={};
	var BASE_URL="http://localhost:8084/BlogMiddle/";
	
	notificationService.getAllNotifications=function(){
		return $http.get(BASE_URL+"getAllNotification");
	}
	
	notificationService.getNotification=function(id){
		return $http.get(BASE_URL+"getNotification/"+id);
	}
	
	notificationService.updateNotification=function(id){
		return $http.post(BASE_URL+"updateNotification/"+id);
	}
	
	return notificationService;
});