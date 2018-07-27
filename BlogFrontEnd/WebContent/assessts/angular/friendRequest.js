app.controller("FriendRequestController",function($scope,$http,$log){
	$scope.friends;
	$scope.message;
	
    $scope.friendRequests=function()
    {
    	console.log("Friends Requests Function");
    	$scope.friends=$http.get('http://localhost:8084/BlogMiddle/pendingRequests').then(function(response) {
    		var arr=[];
			var length=response.data.length;
			if(length>0){
			for(i=0;i<response.data.length;i++)
				{
				friend={"email":"","toId":"","fromId":"","status":""};
				friend=response.data[i];
				arr[i]=friend;
				}
	    	
			$scope.friends=arr;
			
			$log.info(response);
		}
			else $scope.message="No pending Requests Found";
    	},
    	function(reason) {
			$scope.error = reason.data;
		});
    }
});
