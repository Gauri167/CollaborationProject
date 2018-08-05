app.controller("FriendListController",function($scope,$http,$log,$location,$rootScope){
	$scope.friends;
	
	
	console.log("Friend List Controller");
	
	 $scope.friendsList=function()
	    {
	    	console.log("Friends List Function");
	    	$http.get('http://localhost:8084/BlogMiddle/friendsList').then(function(response) {
	    		var arr=[];
				
				for(i=0;i<response.data.length;i++)
					{
					friend={"email":""};
					friend=response.data[i];
					arr[i]=friend;
					}
		    	
				$scope.friends=arr;
				
				$log.info(response);
			}, function(reason) {
				$scope.error = reason.data;
			});
	    }
	 
	 $scope.startChat=function(email){
		 $location.url('/chat');
		 alert(email)
		 $rootScope.emailId=email;
	 }
});