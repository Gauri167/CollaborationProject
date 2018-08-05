app.controller("ChatController",function($scope,$http,$log,$rootScope,$window){
	$scope.chat={
		"toId":'',
		"message":''
	};
	
	$scope.messages
	
	console.log("Chat Controller");
	
	$scope.chatFunc=function(emailId){
		console.log("Chat Function");
		alert(emailId)
	    $scope.chat.toId=emailId;
	    alert($scope.chat.toId)
		$http.post('http://localhost:8084/BlogMiddle/saveMessage',$scope.chat).then(function(response){
			 $scope.messageList();
			console.log('Status Text:'+response.statusText);
		})
	}
	
	$scope.messageList=function(){
		console.log("Message List Function");
		alert($rootScope.emailId)
		$http.post('http://localhost:8084/BlogMiddle/getMessages/'+$rootScope.emailId).then(function(response){
			console.log('Status Text:'+response.statusText);
			$scope.messages=response.data;
		})
	}
})