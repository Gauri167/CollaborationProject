app.controller('ChatCtrl',function($scope,$rootScope,ChatService,$http){
	
	$scope.messages=[];
	
	
	
    
	
	$scope.message="";
	$scope.max=250;
	
	$scope.addMessage=function()
	{
		ChatService.send($scope.message);
		$scope.message="";
	};
	ChatService.receive().then(null,null,function(message){
		//$scope.messages=message.data;
		alert("Recieved Message : "+message);
		$scope.messages.push(message);
	});
		
});