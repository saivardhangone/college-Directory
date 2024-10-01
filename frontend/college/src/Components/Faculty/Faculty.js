import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { useLocation } from 'react-router-dom';
import './Faculty.css'; 

const Faculty = () => {
  const [students, setStudents] = useState([]);
  const location = useLocation();
  const { facultyId } = location.state;
  const [facultyDetails, setFacultyDetails] = useState({
    fullName: '',
    department: '',
    email: '',
    phoneNumber: '',
    faculty: {
      officeHours: ''
    }
  });
  const [updateData, setUpdateData] = useState({
    id: '',
    officeHours: ''
  });

  // Fetch student list
  useEffect(() => {
    axios.get('http://localhost:8081/college/students')
      .then(response => setStudents(response.data))
      .catch(error => console.error('Error fetching student data:', error));
  }, []);

  
  useEffect(() => {
    if (facultyId !== undefined && facultyId !== null) {
      axios.get(`http://localhost:8081/college/${facultyId}`)
        .then(response => {
          setFacultyDetails(response.data);
          setUpdateData({ id: facultyId, officeHours: response.data.faculty?.officeHours || '' });
        })
        .catch(error => console.log(error));
    }
  }, [facultyId]);

  // Handle office hours change
  const handleOfficeHoursChange = (e) => {
    setUpdateData({ ...updateData, officeHours: e.target.value }); // Use updateData instead of facultyDetails
  };

  // Update office hours
  const handleUpdateOfficeHours = () => {
    axios.put(`http://localhost:8081/faculty/`, updateData)
      .then(() => {
        alert('Office hours updated successfully');
      })
      .catch(error => console.error('Error updating office hours:', error));
  };

  return (
    <div className="admin-dashboard">
      <h1>Faculty Dashboard</h1>

    
      <div className="table-section">
        <h2>Student List</h2>
        <table className="styled-table">
          <thead>
            <tr>
              <th>Name</th>
              <th>Department</th>
              <th>Year</th>
              <th>Contact</th>
            </tr>
          </thead>
          <tbody>
            {students.map(student => (
              <tr key={student.id}>
                <td>{student.fullName}</td>
                <td>{student.student.departmentId.name}</td>
                <td>{student.student.year}</td>
                <td>{student.email}, {student.phoneNumber}</td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>

      <div className="faculty-section">
        <h2>Faculty Details</h2>
        <table className="styled-table">
          <tbody>
            <tr>
              <td>Name:</td>
              <td>{facultyDetails.fullName}</td>
            </tr>
            <tr>
              <td>Email:</td>
              <td>{facultyDetails.email}</td>
            </tr>
            <tr>
              <td>Phone:</td>
              <td>{facultyDetails.phoneNumber}</td>
            </tr>
            <tr>
              <td>Office Hours:</td>
              <td>
                <input
                  type="text"
                  value={facultyDetails.faculty?.officeHours || ''} 
                  onChange={handleOfficeHoursChange}
                  placeholder="Enter hours"
                />
              </td>
            </tr>
          </tbody>
        </table>
        <button onClick={handleUpdateOfficeHours}>Update Office Hours</button>
      </div>
    </div>
  );
};

export default Faculty;
