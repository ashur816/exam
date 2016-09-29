$(document).ready(function () {
    var t = $("#table").dataTable({
        "showRowNumber": true,
        "aoColumnDefs": [
            /*操作列定义*/
            {
                "sTitle": "ID",
                "targets": [0],
                "data": "userId",
                "bVisible": false
            },
            {
                "sTitle": "序号",
                "targets": [1],
                "data": null,
            },
            {
                "sTitle": "姓名",
                "targets": [2],
                "data": "fullname",
                "bSortable": false
            },
            {
                "sTitle": "开始时间",
                "targets": [3],
                "data": "startTime",
                "bSortable": true
            },
            {
                "sTitle": "结束时间",
                "targets": [4],
                "data": "endTime",
                "bSortable": true
            },
            {
                "sTitle": "分数",
                "targets": [5],
                "data": "totalGoal",
                "bSortable": true
            },
            {
                "sTitle": "操作",
                "targets": [6], //删除；修改
                "data": null,
                "bSortable": false,
                "defaultContent": "<button id='delRow' class='btn btn-primary' type='button'><i class='fa fa-trash-o'>删除</i></button>&nbsp;&nbsp;<button id='editRow' class='btn btn-primary' type='button'><i class='fa fa-edit'>修改</i></button>&nbsp;&nbsp;<button id='showRow' class='btn btn-primary' type='button'><i class='fa fa-trash-o'>查看</i></button>&nbsp;&nbsp;<button id='mark' class='btn btn-primary' type='button'><i class='fa fa-trash-o'>打分</i></button>"
            }
        ],
        /*添加索引列*/
        "fnDrawCallback": function () {
            var api = this.api();
            var startIndex = api.context[0]._iDisplayStart;
            api.column(1).nodes().each(function (cell, i) {
                cell.innerHTML = startIndex + i + 1;
            });
        }
    });

    refreshTable();

    function refreshTable() {
        $.get(baseUrl + "/getUserList/0?token=" + token, function (data, status) {
            if (data != "") {
                t.fnClearTable();
                t.fnAddData(data, true);
            } else {
                alert("暂时没有数据");
            }
        });
    }

    // function newData(data) {
    //     var a = []; //定义一个空数组存放显示的数据
    //     $.each(data, function (n, value) {
    //         var tempObject = {};
    //         tempObject.userId = value.userId || "";
    //         tempObject.fullname = value.fullname || "";
    //         tempObject.startTime = value.startTime || "";
    //         tempObject.endTime = value.endTime || "";
    //         tempObject.grade = value.totalGoal || "";
    //         a.push(tempObject);
    //     });
    //     return a;
    // }

    /*修改*/
    $('#table tbody').on('click', 'button#editRow', function () {
        var data = t.api().row($(this).parents('tr')).data(); //获取点击行的数据
        /*显示信息*/
        $.ajax({
            type: "get",
            url: baseUrl + "/getUserList/" + data["userId"] + "?token=" + token,
            success: function (response) {
                setDataToForm("editForm", response[0]);
                $("input", $("#editForm")).remove("attr", "readonly");
                $("#editForm [select]").attr("disabled", "false");
            },
            error: function () {
                console.log("ERROR!");
            }
        });
        $("#editModal").modal("show");
    });

    /*保存修改*/
    $("#btnSave").click(function () {
        var data = $("#editForm").serialize();
        $.ajax({
            type: "POST",
            url: baseUrl + "/updateUser?token=" + token,
            data: data,
            success: function (response) {
                alert(response.message);
                $("#editModal").modal("hide");
                refreshTable();
            },
            error: function () {
                console.log("error!");
                alert("error");
            }
        });
    });

    /*删除*/
    $('#table tbody').on('click', 'button#delRow', function () {
        if (show() == true) {
            var data = t.api().row($(this).parents('tr')).data();x
            $.ajax({
                url: baseUrl + "/deleteUser?token=" + token + "&userId=" + data["userId"],
                type: 'POST',
            }).done(function (message) {
                Huimodal_alert(response.message, 1000);
                $("#editModal").modal("hide");
            }).fail(function () {
                Huimodal_alert("error", 1000);
            });
        } else {
            return false;
        }
        refreshTable();
    });

    /*查看*/
    $('#table tbody').on('click', 'button#showRow', function () {
        var data = t.api().row($(this).parents('tr')).data();
        $.ajax({
            type: "get",
            url: baseUrl + "/getUserList/" + data["userId"] + "?token=" + token,
            success: function (response) {
                setDataToForm("editModal", response[0]);
                $("input", $("#editForm")).attr("readonly", "readonly");
                $("select", $("#editForm")).attr("disabled", "true");
            },
            error: function () {
                console.log("error!");
            }
        });
        $("#editModal").modal("show");
    });

    /*打分*/
    $('#table tbody').on('click', 'button#mark', function () {
        var data = t.api().row($(this).parents('tr')).data();
        var data_end = {
            "endTime": data["endTime"],
            "userId": data["userId"],
            "grade": data["grade"]
        };
        if (data_end.endTime != "") {
            window.location.href = baseUrl + "/exam-detail.html?userId=" + data_end.userId + "&token=" + token;
        } else if (data_end.endTime == "") {
            alert("该人员还在答题中...");
        }
    });

    /*新增人员*/
    $("#btnAdd").click(function () {
        $.ajax({
            type: "POST",
            url: baseUrl + "/register?token=" + token,
            data: $("#addForm").serialize(),
            async: false,
            success: function (response) {
                if (response.token != "") {
                    $("#addModal").modal("hide");
                    refreshTable();
                    location.reload();
                    alert(response.message);
                } else {
                    console.log(response.message);
                }
            },
            error: function () {
                console.log("Connection error");
            }
        });
    });
});
