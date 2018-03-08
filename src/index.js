import React from 'react';
import { render } from 'react-dom';


class ProjectTable  extends React.Component {

    render() {
        var rows = [];
        this.props.projects.forEach(function(project) {         //pushes to table the row it creates from projects var
            rows.push(<Project project={project} />);
        });
        return (
            <div className="container">
                <table className="table table-striped">
                    <thead>
                    <tr>
                        <th>Title</th><th>Description</th><th>Programs</th><th>Max allowed</th><th>Remove</th>
                    </tr>
                    </thead>
                    <tbody>{rows}</tbody>
                </table>
            </div>
        );
    }
}

class Project  extends React.Component {

    handleClick(event){

        //remove project from table

}
    render() {
        return(
            <tr>
                <td>{this.props.project.Title}</td>
                <td>{this.props.project.Description}</td>
                <td>{this.props.project.Programs}</td>
                <td>{this.props.project.MaxAllowed}</td>
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
        var restrictedTo = target.type === 'checkbox' ? target.checked :[
            document.getElementById('SE').value,
            document.getElementById('EE').value,
            document.getElementById('Communications').value,
            document.getElementById('CSE').value,
            document.getElementById('Biomed').value,
            document.getElementById('Mechanical').value,
            document.getElementById('Aerospace').value,
        ];
        var maxAllowed = document.getElementById('pMaxAllowed').value;



        //connect with database here
    }

    render() {


        return (
            <div className="container">Create a new project:
                <form onSubmit={this.handleSubmit}>
                    <label>Title: </label><input id='pTitle' type="text" placeholder="Title..."/><br></br>
                    <label>Description: </label><textarea id='pDescription' type="text"
                                                          placeholder="Description..."/><br></br>

                    <label>Restricted to: </label>
                    <div>
                        <label>SE <input id='SE' type="checkbox"/></label><br></br>
                        <label>EE <input id='EE' type="checkbox"/></label><br></br>
                        <label>Communications <input id='Communications' type="checkbox"/></label><br></br>
                        <label>CSE <input id='CSE' type="checkbox"/></label><br></br>
                        <label>Biomed <input id='Biomed' type="checkbox"/></label><br></br>
                        <label>Mechanical <input id='Mechanical' type="checkbox"/></label><br></br>
                        <label>Aerospace <input id='Aerospace' type="checkbox"/></label><br></br>
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