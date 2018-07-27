app.controller("ChatController",function($scope,$http,$log,$rootScope){
	$scope.chat={
		"toId":'',
		"message":''
	};
	
	$scope.messages
	
	console.log("Chat Controller");
	
	$scope.chatFunc=function(){
		console.log("Chat Function");
		$http.post('http://localhost:8084/BlogMiddle/saveMessage',$scope.chat).then(function(response){
			console.log('Status Text:'+response.statusText);
			$rootScope.emailId='';
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