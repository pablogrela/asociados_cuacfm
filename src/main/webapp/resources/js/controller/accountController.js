/*
 * Copyright © 2015 Pablo Grela Palleiro (pablogp_9@hotmail.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
membersApp.controller('AccountController', [ '$scope', 'AccountService', function($scope, AccountService) {
	$scope.sortType;
	$scope.search;
	$scope.sortReverse = false;
	$scope.numPerPage = 20;
	$scope.account;
	$scope.accounts;
	$scope.message;

	$scope.fetchAllUsers = function() {
		AccountService.fetchAllUsers().then(function(data) {
			$scope.accounts = data;
		}, function(errorResponse) {
			console.error('Error while fetching accounts', errorResponse);
		});
	}

	$scope.unsubscribe = function(id) {
		AccountService.unsubscribe(id).then(function(data) {
			$scope.message = data;
			$scope.fetchAllUsers();
			showModal(modal);
		}, function(errorResponse) {
			console.error('Error while unsubscribe account', errorResponse);
		});
	}

	$scope.subscribe = function(id) {
		AccountService.subscribe(id).then(function(data) {
			$scope.message = data;
			$scope.fetchAllUsers();
			showModal(modal);
		}, function(errorResponse) {
			console.error('Error while subscribe account', errorResponse);
		});
	}

	$scope.push = function(id, title, body) {
		if (title != null && !jQuery.isEmptyObject(title) && body != null && !jQuery.isEmptyObject(body)) {
			AccountService.push(id, title, body).then(function(data) {
				$scope.message = data;
				$('#closePush').click();
				$scope.title = '';
				$scope.body = '';
			}, function(errorResponse) {
				console.error('Error while push account', errorResponse);
			});
		}
	}

	$scope.email = function(id, title, body) {
		if (title != null && !jQuery.isEmptyObject(title) && body != null && !jQuery.isEmptyObject(body)) {
			AccountService.email(id, title, body).then(function(data) {
				$scope.message = data;
				$('#closeEmail').click();
				$scope.title = '';
				$scope.body = '';
			}, function(errorResponse) {
				console.error('Error while push account', errorResponse);
			});
		}
	}
	
	$scope.infoAccount = function(aux) {
		$scope.account = aux;
	}

	$scope.localeSensitiveComparator = function(v1, v2) {
		if (v1.type == 'string' || v2.type == 'string') {
			return v1.value.localeCompare(v2.value);
		}
		return (v1.value < v2.value) ? -1 : 1;
	};

} ]);
