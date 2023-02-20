var app = angular.module("myApp", ['ngRoute'])

app.config(function ($routeProvider) {
    $routeProvider
        .when("/", {
            templateUrl: "home.html",
            controller: "courseController"
        })
        .when("/course", {
            templateUrl: "course.html",
            controller: "courseController"
        })
        .when("/introduce", {
            templateUrl: "introduce.html"
        })
        .when("/contact", {
            templateUrl: "contact.html"
        })
        .when("/qa", {
            templateUrl: "qa.html"
        })
        .when("/quiz/:id/:name", {
            templateUrl: "quizpoly.html",
            controller: "quizController"
        })
        .when("/login", {
            templateUrl: "login.html",
            controller: "loginController"
        })
        .when("/register", {
            templateUrl: "register.html",
            controller: "registerController"
        })
        .when("/changeInfo", {
            templateUrl: "changeInfo.html",
            controller: "changInfoContronller"
        })
        .when("/forgetPassword", {
            templateUrl: "forgetPassword.html",
            controller: "forgetPasswordController"
        })

})


app.controller("registerController", function ($scope, $http, $location) {
    $scope.student = {}

    $scope.save = function ($event) {
        $event.preventDefault();

        if ($scope.student.password != $scope.student.rePassword) {
            alert("Vui lòng nhập trùng mật khẩu")
            return;
        }

        let params = { ...$scope.student };

        if ($scope.student) {
            $http.post('http://localhost:8080/students', params).then(resp => {
                alert("Đăng ký thành công!")
                $scope.clear()
                $location.path('/login')
            }).catch(error => {
                alert("Đăng ký thất bại!")
            })
        }
    }

    $scope.clear = function () {
        $scope.student = {}
    }
})

app.controller("loginController", function ($scope, $rootScope, $http, $location) {
    $rootScope.isLogin = false;

    $scope.checkLogin = function () {

        if ($scope.student) {
            let params = { ...$scope.student };

            $http.post('http://localhost:8080/students/checkLogin', params).then(function (resp) {
                // alert(resp.status)

                if (resp.data == false) {
                    alert("Đăng nhập không thành công!")
                } else {
                    alert("Đăng nhập thành công!")
                    var student = {}
                    student = resp.data
                    console.log(student.email)

                    sessionStorage.setItem("student", JSON.stringify(student));

                    var stu = {}
                    stu = JSON.parse(sessionStorage.getItem("student"))
                    // console.log(stu.email)
                    $location.path('/')
                    $rootScope.isLogin = true;
                }
            }).catch(error => {
                alert(error)
            })
        }


    }

    $rootScope.logout = function () {
        sessionStorage.removeItem("student")
        $rootScope.isLogin = false;

    }
})

app.controller("changInfoContronller", function ($scope, $http, $location) {


    $scope.studentSesion = JSON.parse(sessionStorage.getItem("student"));

    $scope.changeInfo = function (e) {
        e.preventDefault()
        // console.log($scope.student.fullname)
        // console.log($scope.student.birthday)
        // console.log($scope.student.fullname)



        var params = { ...$scope.student }
        $http.put('http://localhost:8080/students/updateInfo/' + $scope.studentSesion.username, params).then(function (resp) {

            if (resp.data) {
                sessionStorage.setItem("student", JSON.stringify(resp.data))
                var student = JSON.parse(sessionStorage.getItem("student"))
             
                alert("Thay đổi thông tin thành công")
                $scope.clear()
                // $location.path('/')
                // $location.path('/changeInfo')
            } else {
                alert("Thay đổi thông tin không thành công")
            }
        }).catch(error => {
            alert(error)
        })

    }

    $scope.clear = function () {
        $scope.student = {}
    }

    $scope.changePass = function (e) {
        e.preventDefault()

        if ($scope.form.newPassword != $scope.form.rePassword) {
            alert("Vui lòng mật trùng mật khẩu!")
            return;
        }

        var params = { ...$scope.form }
        // var newPassword = { ...$scope.newPassword }
        $http.put('http://localhost:8080/students/changepass/' + $scope.studentSesion.username, params).then(function (resp) {
            alert("Thay đổi mật khẩu thành công")
            $scope.form = {}
        }).catch(function (error) {
            alert("Thay đổi mật khẩu không thành công")
        })

    }

})

app.controller("forgetPasswordController", function ($scope, $http) {

    $scope.forgetPassword = function () {



        var params = { ...$scope.student }

        $http.post('http://localhost:8080/students/forgetPass', params).then(function (resp) {

            $scope.student = resp.data

            if (!$scope.student) {
                alert("Không tìm thấy tài khoản")
                $scope.student = {}
                return
            } else {
                alert("Quên mật khẩu thành công")
            }
        })


    }
})

app.controller("courseController", function ($scope,$rootScope ,$http, $location) {


    $scope.listSubject = [];
    $scope.pageCount = 0;
    $http.get("db/Subjects.js").then(function (resp) {
        $scope.listSubject = resp.data
        $scope.pageCount = Math.ceil($scope.listSubject.length / 8)
    })

    $scope.begin = 0;

    $scope.redirecQuiz = function (id,name) {
        if ($rootScope.isLogin) {
            $location.path("/quiz/"+id+"/"+name)
        }else{
            alert("Vui lòng đăng nhập!")
            $location.path("/login")
            
        }
    }

    $scope.first = function () {
        $scope.begin = 0;
    }

    $scope.last = function () {
        $scope.begin = ($scope.pageCount - 1) * 8;
    }

    $scope.prev = function () {
        // if ($scope.begin >= 0) {
        $scope.begin -= 8;
        // }
    }

    $scope.next = function () {
        console.log($scope.pageCount)
        if ($scope.begin < (($scope.pageCount - 1) * 8)) {
            $scope.begin += 8;
        }
    }

})

app.controller("quizController", function ($scope, $http, $routeParams, quizFactory) {

    $http.get("db/Quizs/" + $routeParams.id + ".js").then(function (resp) {
        quizFactory.questions = resp.data
    })

})

app.directive('quizzfpoly', function (quizFactory, $routeParams) {
    return {
        restrict: 'E',
        scope: {},
        templateUrl: 'quiz.html',
        link: function (scope, elem, attrs) {

            scope.start = function () {
                quizFactory.getQuestions().then(function () {
                    scope.quizOver = false;
                    scope.id = 0
                    scope.inProgess = true;
                    scope.getQuestion()
                })

            }

            scope.reset = function () {
                scope.subjectName = $routeParams.name
                scope.marks = 0
                scope.answerMode = true;
                scope.inProgess = false;
            }

            scope.getQuestion = function () {
                var quiz = quizFactory.getQuestion(scope.id)

                if (quiz) {
                    scope.question = quiz.Text
                    scope.options = quiz.Answers
                    scope.answer = quiz.AnswerId
                } else {
                    scope.quizOver = true;
                }
            }

            scope.nextQuestion = function () {
                scope.id++;
                scope.getQuestion()
                scope.answerMode = true;
            }

            scope.checkAnsewr = function () {
                if (!$('input[name = answer]:checked').length) return

                var ans = $('input[name = answer]:checked').val();

                if (ans == scope.answer) {
                    scope.marks++;
                    alert("Bạn đã trả lời đúng!")
                } else {
                    alert("Bạn đã trả lời sai!")
                }

                scope.answerMode = false;
            }

            scope.reset();
        }
    }
})

app.factory("quizFactory", function ($http, $routeParams) {
    return {
        getQuestions: function () {
            return $http.get('db/Quizs/' + $routeParams.id + '.js').then(function (res) {
                questions = res.data
            });
        },
        getQuestion: function (id) {

            var randItem = questions[Math.floor(Math.random() * questions.length)];

            if (id < 10) {
                return randItem;
            } else {
                return false;
            }

        }
    }
})