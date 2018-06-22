app.controller("JobController",function($scope,$http,$location,$rootScope){
	$scope.job={"jobDesignation":'',"jobDescription":'',"company":'',"location":'',"salary":'',"applyLastDate":''};
	$scope.addJob=function(){
		console.log("Add Job function")
		$http.post('http://localhost:8084/BlogMiddle/addJob',$scope.job).then(function(response){
			$scope.message=response.statusText;
			console.log('Status Text:'+response.statusText);
		});
	}
});