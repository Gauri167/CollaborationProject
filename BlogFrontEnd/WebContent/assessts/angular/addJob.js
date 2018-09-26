app.controller("JobController",function($scope,$http,$location,$rootScope){
	$scope.job={"jobDesignation":'',"jobDescription":'',"company":'',"location":'',"salary":'',"applyLastDate":''};
	$scope.ajob={"username":'',"qualification":'',"jobId":''}
	$scope.addJob=function(){
		console.log("Add Job function")
		$http.post('http://localhost:8084/BlogMiddle/addJob',$scope.job).then(function(response){
			$scope.message=response.statusText;
			console.log('Status Text:'+response.statusText);
		});
	}
	
	$scope.showActvJobs=function(){
		console.log("Show active Jobs")
		$http.get('http://localhost:8084/BlogMiddle/activeJobs').then(function(response){
			$scope.activeJobs=response.data;
		});
	}
	
	$scope.inactiveJobs=function(){
		console.log("Show inactive jobs")
		$http.get('http://localhost:8084/BlogMiddle/inactiveJobs').then(function(response){
			$scope.inactvJobs=response.data;
		});
	}
	
	$scope.apply=function(){
		console.log("Apply for Job")
		$scope.ajob.jobId=$rootScope.jobId;
		$http.post('http://localhost:8084/BlogMiddle/applyJob',$scope.ajob).then(function(response){
			$scope.message=response.statusText;
			console.log('Status Text:'+response.statusText);
		});
	}
    
	$scope.toapply=function(id){
		console.log("apply");
		$rootScope.jobId=id;
		$location.url('/applyJob');
	}
});