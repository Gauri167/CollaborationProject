app.controller('ChatCtrl',function($scope,$rootScope,ChatService,$http){
	
	$scope.messages=[];
	
	$scope.uname;
	
	$scope.message="";
	$scope.max=250;
	
	$scope.addMessage=function(username)
	{
		ChatService.send($scope.message);
		$scope.message="";
		$scope.uname=username;
	};
	ChatService.receive().then(null,null,function(message){
		//$scope.messages=message.data;
		alert("Recieved Message : "+message);
		$scope.messages.push(message);
	});
		
});