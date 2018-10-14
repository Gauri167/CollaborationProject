app.controller("NotificationController",function($scope,NotificationService,$routeParams,$rootScope,$location){
	var id=$routeParams.id;
	
	function getAllNotification(){
		NotificationService.getAllNotifications().then(function(response){
			$rootScope.notifications=reponse.data;
			$rootScope.notificationsCount=$rootScope.notifications.length;
		})
	}
	
	if(id!=undefined){
		
		NotificationService.getNotification(id).then(function(response){
			$scope.notification=response.data;
		})
		
		NotificationService.updateNotification(id).then(function(response){
			getAllNotification()
		})
	}
	
	getAllNotification()
})