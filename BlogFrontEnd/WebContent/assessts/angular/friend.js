app.controller("FriendController",function($scope,$http,$log){
	$scope.suggestedUser={"email":'',"username":''};
	$scope.friend={"toId":'',"fromId":'',"status":''};
	$scope.addFrnd={"email":''};
	$scope.suggestedUsers;
	$scope.friends;
	
	console.log("Friend Controller");
	
	$scope.suggestedUsers=function()
	{
		console.log("suggested users function");
	    $http.get('http://localhost:8084/BlogMiddle/suggestedUsers').then(function(response) {
			
		var arr=[];
			
			for(i=0;i<response.data.length;i++)
				{
				suggestedUser={"email":"","username":""};
				suggestedUser=response.data[i];
				arr[i]=suggestedUser;
				}
	    	
			$scope.suggestedUsers=arr;
			
			$log.info(response);
		}, function(reason) {
			$scope.error = reason.data;
		});
	}
	    
	    $scope.addFriend=function(email)
	    {
	    	console.log("Add Friend Function");
	    	$http.post('http://localhost:8084/BlogMiddle/addFriendRequest/'+email).then(function(response) {
	    		alert($scope.addFrnd);
				$scope.message = response.statusText;
				console.log('Status Text:' + response.statusText);
			});
	    }
	    
	    $scope.friendRequests=function()
	    {
	    	console.log("Friends Requests Function");
	    	$scope.friends=$http.get('http://localhost:8084/BlogMiddle/pendingRequests').then(function(response) {
				$scope.message = response.statusText;
				console.log('Status Text:' + response.statusText);
			});
	    }
	    
	    $scope.friendsList=function()
	    {
	    	console.log("Friends List Function");
	    	$scope.friends=$http.get('http://localhost:8084/BlogMiddle/friendsList').then(function(response) {
				$scope.message = response.statusText;
				console.log('Status Text:' + response.statusText);
			});
	    }

});