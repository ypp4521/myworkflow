var qfBpmAssignmentSelectCtrl = [ '$scope','$http', function($scope,$http) {
	$scope.mySelections = [];  
    //Grid 筛选  
    $scope.filterOptions = {  
        filterText: '',  
        useExternalFilter: false  
    };  
    //总共条目  
    $scope.totalServerItems = 10;  
    //设置相关页面数据  
    $scope.pagingOptions = {  
        pageSizes: [20, 50, 100],//page 行数可选值  
        pageSize: 20, //每页行数  
        currentPage: 1, //当前显示页数  
    };  
	$scope.gridAssignmentOptions = { data : 'assignData' ,
							selectedItems: $scope.mySelections,  
				            showSelectionCheckbox: false,  
				            multiSelect: false,  
				            showGroupPanel: false,  
				            showColumnMenu: true,  
				            enableCellSelection: true,  
				            enableCellEditOnFocus: false,  
				            enablePaging: true,  
				            showFooter: true,  
				            totalServerItems: $scope.totalServerItems,  
				            filterOptions: $scope.filterOptions,  
				            pagingOptions: $scope.pagingOptions,  
				            i18n:'zh-cn',  
						   columnDefs: [{field: 'name', displayName: '姓名'}, 
					                    {field:'nameStr', displayName:'真实姓名'}]};
	$scope.$watch('pagingOptions', function () {  
		$scope.getPagedDataAsync($scope.pagingOptions.pageSize, $scope.pagingOptions.currentPage, $scope.filterOptions.filterText);  
    },true);  
    $scope.getPagedDataAsync = function (pageSize, page, searchText) {
    	
    }
	$scope.close=function(){
		$scope.$hide();
	}
	$scope.save=function(){
		if($scope.mySelections&&$scope.mySelections.length==1){
			$scope.property.value.assignment.assignee =$scope.mySelections[0].id;
		}
		$scope.close();
		$scope.assignmentPopup();
	}
}];