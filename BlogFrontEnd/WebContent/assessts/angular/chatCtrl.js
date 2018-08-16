app.controller('ChatCtrl',function($scope,$rootScope,ChatService,$http){
	
	$scope.messages;
	
	
	ChatService.receive().then(null,null,function(message){
		//$scope.messages=message.data;
		$scope.messages.push(message);
	});
    
	
	$scope.message="";
	$scope.max=250;
	
	$scope.addMessage=function()
	{
		ChatService.send($rootScope.currentUser.username+" : "+$scope.message);
		$scope.message="";
	};
		
});