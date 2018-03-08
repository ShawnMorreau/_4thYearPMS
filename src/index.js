import React from 'react';
import sql from 'mysql';
import axios from 'axios';
import { render } from 'react-dom';


function runQuery(query){
    var connection = sql.createConnection({
        host: 'localhost',
        user: 'user',
        password: 'password',
        database: 'sysc'
    });

    connection.connect();
    connection.query(query, function (err, rows, fields) {
        if (err) throw err

        console.log('The solution is: ', rows[0].solution)
    });
    connection.end();
};

class ProjectTable  extends React.Component {

    constructor(props) {
        super(props);

        this.state = {
            projects: []
        };

        this.fetchProjects = this.fetchProjects.bind(this);
    }

    componentDidMount() {
        this.fetchProjects()
            .then((results)=>this.setState({ projects: results }));
    }

    fetchProjects() {
        let projects = new Promise((resolve, reject) => {
            const url = 'http://localhost:8080/project/all';

            axios.get(url)
                .then(function (response) {
                    console.log("Something");
                    console.log(response.data);
                    resolve(response.data);
                })
                .catch(function (error) {
                    console.log("NotGood");
                    console.log(error);
                    reject(error);
                });
        });

        return projects;
    }

    render() {
        console.log("RENDERING");
        //runQuery('Select * from project');

        var rows = [];
        console.log("PROJECTS");
        console.log(this.state.projects);
        this.state.projects.forEach(function(project) {         //pushes to table the row it creates from projects var
            rows.push(<Project project={project} />);
        });
        return (
            <div className="container">
                <table className="table table-striped">
                    <thead>
                    <tr>
                        <th>Id</th><th>Title</th><th>Description</th><th>Programs</th><th>Max allowed</th><th>Remove</th>
                    </tr>
                    </thead>
                    <tbody>
                    {rows}
                    </tbody>
                </table>
            </div>
        );
    }
}

class Project  extends React.Component {

    constructor(props) {
        super(props);
        this.handleClick = this.handleClick.bind(this);

    }

    handleClick(event){

        this.deleteProject(this.props.project.id)
            .then((result)=>this.forceUpdate());
        console.log(event);
        console.log(this.props.project.id);
    }

    deleteProject(id) {
        let project = new Promise((resolve, reject) => {
            const url = `http://localhost:8080/project/deleteById?id=${id}`;

            axios.get(url)
                .then(function (response) {
                    console.log("Something");
                    console.log(response.data);
                    resolve(response.data);
                })
                .catch(function (error) {
                    console.log("NotGood");
                    console.log(error);
                    reject(error);
                });
        });

        return project;
    }

    render() {
        //runQuery('Select * from project');
        return(
            <tr>
                <td>{this.props.project.id}</td>
                <td>{this.props.project.title}</td>
                <td>{this.props.project.description}</td>
                <td>{this.props.project.programs}</td>
                <td>{this.props.project.studentLimit}</td>
                <td><button onClick={this.handleClick}>X</button></td>
            </tr>
        );
    }
}

var PROJECTS = [ {name: 'Joe Biden', age: 45, years: 5, years: 5},      //dummy data to test table
    {name: 'President Obama', age: 54, years: 8},                       //will be replaced with the database
    {name: 'Crystal Mac', age: 34, years: 12},
    {name: 'James Henry', age: 33, years: 2}];

class ProjectForm extends React.Component {     //structure of the form


    handleSubmit(event) {           //grabs values from the form and creates variables
        event.preventDefault();
        const target = event.target;

        var title = document.getElementById('pTitle').value;
        var description = document.getElementById('pDescription').value;
 /*       var restrictedTo = target.type === 'checkbox' ? target.checked :[
            document.getElementById('SE').checked,
            document.getElementById('EE').checked,
            document.getElementById('Communications').checked,
            document.getElementById('CSE').checked,
            document.getElementById('Biomed').checked,
            document.getElementById('Mechanical').checked,
            document.getElementById('Aerospace').checked,
        ];*/
        var maxAllowed = document.getElementById('pMaxAllowed').value;
        var p = [];
        var checks = document.getElementsByName("programs[]");
        for (var i=0; i < checks.length; i++) {
            if(checks[i].checked){
                p.push(checks[i].value);
            }
        }
        var programs = p.join();
        this.addProject(title, description, programs, maxAllowed).then((result)=>this.forceUpdate());

    }

    addProject(title, description, programs, maxStudents) {
        let project = new Promise((resolve, reject) => {
            const url = `http://localhost:8080/project/add?title=${title}&description=${description}&programs=${programs}&maxStudents=${maxStudents}&`;

            axios.get(url)
                .then(function (response) {
                    console.log("Something");
                    console.log(response.data);
                    resolve(response.data);
                })
                .catch(function (error) {
                    console.log("NotGood");
                    console.log(error);
                    reject(error);
                });
        });

        return project;
    }

    render() {


        return (
            <div className="container">Create a new project:
                <form onSubmit={this.handleSubmit}>
                    <label>Title: </label><input id='pTitle' type="text" placeholder="Title..."/><br></br>
                    <label>Description: </label><textarea id='pDescription' type="text"
                                                          placeholder="Description..."/><br></br>

                    <label>Restricted to: </label>
                    {/*<div>
                        <label>SE <input id='SE' type="checkbox"/></label><br></br>
                        <label>EE <input id='EE' type="checkbox"/></label><br></br>
                        <label>Communications <input id='Communications' type="checkbox"/></label><br></br>
                        <label>CSE <input id='CSE' type="checkbox"/></label><br></br>
                        <label>Biomed <input id='Biomed' type="checkbox"/></label><br></br>
                        <label>Mechanical <input id='Mechanical' type="checkbox"/></label><br></br>
                        <label>Aerospace <input id='Aerospace' type="checkbox"/></label><br></br>
                    </div>*/}
                    <div>
                        <fieldset id="checkArray">
                            <b>SE </b><input type="checkbox" name="programs[]" value="SE" /><br></br>
                            <b>SE </b><input type="checkbox" name="programs[]" value="EE" /><br></br>
                            <b>Communications </b><input type="checkbox" name="programs[]" value="Communications" /><br></br>
                            <b>CSE </b><input type="checkbox" name="programs[]" value="CSE" /><br></br>
                            <b>Biomed </b><input type="checkbox" name="programs[]" value="Biomed" /><br></br>
                            <b>Mechanical </b><input type="checkbox" name="programs[]" value="Mechanical" /><br></br>
                            <b>Aerospace </b><input type="checkbox" name="programs[]" value="Aerospace" /><br></br>
                        </fieldset>
                    </div>
                    <br></br>
                    <label>Max # of Students: </label><input id='pMaxAllowed' type="number"
                                                             placeholder="max # of Students..."/><br></br>
                    <label>Submit: </label><input type="submit" value="Submit"/><br></br>
                </form>
                <ProjectTable projects={PROJECTS}/>
            </div>


        );


    }

}







render(<ProjectForm projects={PROJECTS}/>, document.getElementById('root'));