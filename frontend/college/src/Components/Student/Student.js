import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { useLocation } from 'react-router-dom';
import './Student.css';

const Student = () => {
  const location = useLocation(); 
  const { studentId } = location.state; 
  const [studentProfile, setStudentProfile] = useState({});
  const [searchTerm, setSearchTerm] = useState('');
  const [searchResults, setSearchResults] = useState([]);
  const [facultyAdvisors, setFacultyAdvisors] = useState([]);

  // Fetch student profile
  useEffect(() => {
    if (studentId !== undefined && studentId !== null) {
      axios.get(`http://localhost:8081/college/${studentId}`)
        .then(response => {
          setStudentProfile(response.data);
        })
        .catch(error => console.log(error));
    }
  }, [studentId]);

  // Fetch faculty advisors
  useEffect(() => {
    axios.get('http://localhost:8081/faculty/advisor')
      .then(response => {
        setFacultyAdvisors(response.data);
      })
      .catch(error => console.log(error));
  }, []);

  // Search for other students
  const handleSearch = (e) => {
    setSearchTerm(e.target.value);
    console.log(searchTerm);
  
    if (e.target.value !== '') {
      let searchType = ''; 
  
      if (searchTerm.includes('name:')) {
        searchType = 'name'; // Searching by name
      } else if (searchTerm.includes('year:')) {
        searchType = 'year'; // Searching by year
      } else if (searchTerm.includes('department:')) {
        searchType = 'department'; // Searching by department
      }
  
      let searchValue = searchTerm.split(':')[1]; // Extract the actual search value after the keyword
      let searchUrl = '';
  
      // Determine the URL based on the search type
      if (searchType === 'name') {
        searchUrl = `http://localhost:8081/college/SearchByName/${searchValue}`;
      } else if (searchType === 'year') {
        searchUrl = `http://localhost:8081/college/students/searchByYear?year=${searchValue}`;
      } else if (searchType === 'department') {
        searchUrl = `http://localhost:8081/college/students/searchByDepartment?department=${searchValue}`;
      } else {
        searchUrl = `http://localhost:8081/college/students?search=${e.target.value}`; 
      }
  
      
      axios.get(searchUrl)
        .then(response => {
          setSearchResults(response.data);
        })
        .catch(error => console.log(error));
    } else {
      setSearchResults([]); 
    }
  };
  

  return (
    <div className="student-container">
      {/* Personal Profile Section */}
      <section className="profile-section">
        <h2>Personal Profile</h2>
        <div className="profile-card">
        
          <div className="profile-details">
            <h3>{studentProfile.fullName || 'N/A'}</h3>
            <p>Contact: {studentProfile.phoneNumber ||'N/A'}</p>
            <h4>Academic Information</h4>
            <p>Courses: {studentProfile.student && studentProfile.student.departmentId.name ? studentProfile.student.departmentId.name : "N/A"}</p>
            <p>Year: {studentProfile.student && studentProfile.student.year 
                ? studentProfile.student.year 
                : "N/A"}</p>
          </div>
        </div>
      </section>

      {/* Search for Other Students Section */}
      <section className="search-section">
        <h2>Search for Other Students</h2>
        <input
          type="text"
          placeholder="Search by name, department, or year"
          value={searchTerm}
          onChange={handleSearch}
          className="search-bar"
        />
        {searchResults.length > 0 && (
          <ul className="search-results">
            {searchResults.map(student => (
              <li key={student.id} className="student-list-item">
                <p>Name: {student.name}</p>
                <p>Department: {student.department}</p>
                <p>Year: {student.year}</p>
              </li>
            ))}
          </ul>
        )}
      </section>

      {/* Contact Faculty Advisors Section */}
      <section className="advisors-section">
        <h2>Contact Faculty Advisors</h2>
        <ul className="advisors-list">
          {facultyAdvisors.map(advisor => (
            <li key={advisor.id} className="advisor-list-item">
              <p>Name: {advisor.name}</p>
              <p>Email: <a href={`mailto:${advisor.email}`}>{advisor.email}</a></p>
              <p>Phone: <a href={`tel:${advisor.phone}`}>{advisor.phone}</a></p>
            </li>
          ))}
        </ul>
      </section>
    </div>
  );
};

export default Student;
