

function dynamicTable(tableId) {

    this._tableId = tableId;
    var _table,
        _fields, _headers,
        _defaultText;



    /** Builds the row with columns from the specified names.
     *  If the item parameter is specified, the members of the names array will be used as property names of the item; otherwise they will be directly parsed as text.
     */
    function _buildRowColumns(names, item, id) {
        var row = '<tr>';
        if (names && names.length > 0) {
            $.each(names, function (index, name) {
                var c = item ? item[name + ''] : name;
                row += '<td>' + c + '</td>';
            });

            //id = 1 --> No remove button in the header
            //id = 2 --> No remove button in each row
            if ( id == 0) {


            }else if(id == 1){

            }else if(id == 2){

            }else{
                const id = "remove" + item.id;
                row += '<td><button id=' + id + ' class=\'remove\' value=' + item.id + ' > X </button></td>';
            }
        }

        row += '<tr>';
        return row;
    }

    /** Builds and sets the headers of the table. */
    function _setHeaders() {
        // if no headers specified, we will use the fields as headers.
        _headers = (_headers == null || _headers.length < 1) ? _fields : _headers;
        var h = _buildRowColumns(_headers, '', 1);
        if (_table.children('thead').length < 1) _table.prepend('<thead></thead>');
        _table.children('thead').html(h);
    }

    function _setNoItemsInfo() {
        if (_table.length < 1) return; //not configured.
        var colspan = _headers != null && _headers.length > 0 ?
            'colspan="' + _headers.length + '"' : '';
        var content = '<tr class="no-items"><td ' + colspan + ' style="text-align:center">' +
            _defaultText + '</td></tr>';
        if (_table.children('tbody').length > 0)
            _table.children('tbody').html(content);
        else _table.append('<tbody>' + content + '</tbody>');
    }

    function _removeNoItemsInfo() {
        var c = _table.children('tbody').children('tr');
        if (c.length == 1 && c.hasClass('no-items')) _table.children('tbody').empty();
    }

    return {
        /** Configres the dynamic table. */
        config: function (fields, headers, defaultText) {
            _table = $('#' + tableId);
            _fields = fields || null;
            _headers = headers || null;
            _defaultText = defaultText || 'No items to list...';
            _setHeaders();
            _setNoItemsInfo();
            return this;
        },
        /** Loads the specified data to the table body. */
        load: function (data, append, id) {
            if (_table.length < 1) return; //not configured.

            _setHeaders();
            _removeNoItemsInfo();
            if (data && data.length > 0) {
                var rows = '';
                $.each(data, function (index, item) {
                    rows += _buildRowColumns(_fields, item, id);
                });
                var mthd = append ? 'append' : 'html';
                _table.children('tbody')[mthd](rows);

                let removeButtons = $('.remove');
                for (let i = 0; i < removeButtons.length; i++) {
                    const btnId = "remove" + removeButtons[i].value;
                    $(`#${btnId}`).click(function (e) {
                        dynamicTable.clearRow(e.target.value);
                    });
                }
            } else {
                _setNoItemsInfo();
            }
            return this;
        },
        add: function (data) {
            addProject(data, this.load);
            return this;
        },
        /** Clears the table body. */
        clearRow: function (value) {
            removeProject(value, dynamicTable.load);
            return this;
        },
    };
};

function fetchStudents(callback, append, id) {
    let students = new Promise((resolve, reject) => {
        let url = "";
        if (window.location.hostname.indexOf("localhost") >= 0) {
            url = 'http://localhost:8080/student/all';
        } else {
            url = '/student/all';
        }

        axios.get(url)
            .then(function(response) {
                console.log(response.data);
                resolve(response.data);
            })
            .catch(function(error) {
                console.error("Error fetching students");
                console.log(error);
                reject(error);
            });
    }).then(function(result) {
        callback(result, append, id);
    });

    return students;
}

function fetchProjects(callback, append, id) {
    let projects = new Promise((resolve, reject) => {
        let url = "";
        if (window.location.hostname.indexOf("localhost") >= 0) {
            url = 'http://localhost:8080/project/all';
        } else {
            url = '/project/all';
        }

        axios.get(url)
            .then(function (response) {
                console.log(response.data);
                resolve(response.data);
            })
            .catch(function (error) {
                console.error("Error fetching projects");
                console.log(error);
                reject(error);
            });
    }).then(function (result) {
        callback(result, append, id);
    });

    return projects;
}

function addProject(data, callback) {
    let addProject = new Promise((resolve, reject) => {
        let url = "";
        if (window.location.hostname.indexOf("localhost") >= 0) {
            url = `http://localhost:8080/project/add?title=${data.title}&description=${data.description}&programs=${data.programs}&maxStudents=${data.studentLimit}&`;
        } else {
            url = `/project/add?title=${data.title}&description=${data.description}&programs=${data.programs}&maxStudents=${data.studentLimit}&`;
        }
        axios.get(url)
            .then(function (response) {
                resolve(response.data);
            })
            .catch(function (error) {
                console.error("Error adding new project");
                console.log(error);
                reject(error);
            });
    }).then(function (result) {
        if (result && result === "Saved project") {
            fetchProjects(callback, false);
        } else {
            console.error("Failed to add project");
        }
    });

    return addProject;
}

function removeProject(projectId, callback) {
    let removeProject = new Promise((resolve, reject) => {
        let url = "";
        if (window.location.hostname.indexOf("localhost") >= 0) {
            url = `http://localhost:8080/project/deleteById?id=${projectId}`;
        } else {
            url = `/project/deleteById?id=${projectId}`;
        }
        axios.get(url)
            .then(function (response) {
                resolve(response.data);
            })
            .catch(function (error) {
                console.error("Error deleting project");
                console.log(error);
                reject(error);
            });
    }).then(function (result) {
        if (result && result === "DeleteId") {
            fetchProjects(callback, false);
        } else {
            console.error("Failed to delete project");
        }
    });

    return removeProject;
}

$(document).ready(function (e) {
    //Create the table here by feeding the dynamicTable function values

    //dummy data for testing
    var dummyStudents = [{s: 'adamn', sn: '1234355435', em: 'fudsfhdouf@dfigofg.com', deg: 'SE'},
        {s: 'dfgf', sn: '786545', em: 'fudsfhdouf@dfigofg.com', deg: 'ME'},
        {s: 'wertr', sn: '2344578989', em: 'fudsfhdouf@dfigofg.com', deg: 'EE'}];

    var dummyProject = [{t: 'testing', desc:'testing13242434', p:'robertson'}];

    //Table to display available projects
    var dt = new dynamicTable('projectTable');
    dt.config(['id', 'title', 'description', 'programs', 'studentLimit'], ['ID', 'Title', 'Description', 'Programs', 'Max allowed', 'Remove'], //set to null for field names instead of custom header names
        'There are no items to list...');
    fetchProjects(dt.load, true, 1);

    //Table to display student info
    var studentInfo = new dynamicTable('studentTable');
    studentInfo.config(['name', 'studentId', 'email', 'program'], ['Name', 'Student Number', 'Email', 'Program'], 'There are no students...');
    fetchStudents(studentInfo.load, true, 2);



    //Builds project row for table based on form data
    $("form").submit(function (event) {

        var title = document.getElementById('title').value;
        var description = document.getElementById('description').value;
        var checked = ' ';
        var allowed = document.getElementById('studentLimit').value;

        $('input:checkbox[name=check]:checked').each(function () {
            checked = checked + " " + ($(this).val());
        });

        var data = {
            title: title,
            description: description,
            programs: checked,
            studentLimit: allowed
        };
        dt.add(data);


        checked = ' ';
        return false;
    });
});
