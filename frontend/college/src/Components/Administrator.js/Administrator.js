import React, { useState, useEffect } from 'react';
import axios from 'axios';
import './Administrator.css';

const Administrator = () => {
    const [students, setStudents] = useState([]);
    const [faculties, setFaculties] = useState([]);
    const [roles, setRoles] = useState([]);
    const [department, setDepartment] = useState('');
    const [year, setYear] = useState(0);
    const [showStudentForm, setShowStudentForm] = useState(false);
    const [isUpdating, setIsUpdating] = useState(false);
    const [isAddingFaculty, setIsAddingFaculty] = useState(false);
    const [error, setError] = useState('');
    const [departments, setDepartments] = useState([]);
    const [studentData, setStudentData] = useState({
        fullName: '',
        phoneNumber: '',
        email: '',
        username: '',
        password: '',
        role: 'STUDENT',  
        departmentId: '',
        year: '', 
        photo: '' 
    });

    const [facultyData, setFacultyData] = useState({
        fullName: '',
        phoneNumber: '',
        email: '',
        username: '',
        password: '',
        role: 'FACULTY', 
        officeHours: '', 
        photo: '' ,
        departmentId: '',
    });

  
    useEffect(() => {
        fetchDepartments();
        fetchRoles();  
    }, []);

    const fetchDepartments = async () => {
        try {
            const response = await axios.get('http://localhost:8081/college/department/');
            setDepartments(response.data);
        } catch (error) {
            console.error('Error fetching departments:', error);
        }
    };

    const fetchRoles = async () => {
        try {
            const response = await axios.get('http://localhost:8081/college/roles');
            setRoles(response.data);
        } catch (error) {
            console.error('Error fetching roles:', error);
        }
    };

    const fetchStudentsByDeptAndYear = async () => {
        try {
            const response = await axios.get(`http://localhost:8081/college/student/?department=${department}&year=${year}`);
            if (response.data.length > 0) {
                setStudents(response.data);
                setError("");  
            } else {
                setStudents([]);  
                setError("No data found");
            }
        } catch (error) {
            console.error('Error fetching student data:', error);
        }
    };

    const fetchFacultiesByDept = async () => {
        try {
            console.log(department);
            if(department!=null && department!=''){
                const response = await axios.get(`http://localhost:8081/college/faculty/${department}`);
                setFaculties(response.data);            }
            else{
                setError(" Reuired Department Name")
            }
        } catch (error) {
            console.error('Error fetching faculty data:', error);
        }
    };

    const handleStudentInputChange = (e) => {
        const { name, value } = e.target;
        setStudentData({ ...studentData, [name]: value });
    };

    const handleFacultyInputChange = (e) => {
        const { name, value } = e.target;
        setFacultyData({ ...facultyData, [name]: value });
    };

    const handleStudentSubmit = async (e) => {
        e.preventDefault();
        try {
            if (isUpdating) {
               
                await axios.put(`http://localhost:8081/college/student/${studentData.id}`, studentData);
            } else {
                // Add new student
                await axios.post(`http://localhost:8081/college/student/`, studentData);
            }
            resetForm();
            
        } catch (error) {
            console.error('Error saving student data:', error);
        }
    };

    const handleFacultySubmit = async (e) => {
        e.preventDefault();
        try {
            if (isUpdating) {
                
                await axios.put(`http://localhost:8081/college/faculty/${facultyData.id}`, facultyData);
            } else {
                
                await axios.post(`http://localhost:8081/faculty/`, facultyData);
            }
            resetForm();
                
        } catch (error) {
            console.error('Error saving faculty data:', error);
        }
    };

    const resetForm = () => {
        setStudentData({
            fullName: '',
            phoneNumber: '',
            email: '',
            username: '',
            password: '',
            role: 'STUDENT',
            departmentId: '',
            year: '',
            photo: ''

        });
        
        setFacultyData({
            fullName: '',
            phoneNumber: '',
            email: '',
            username: '',
            password: '',
            role: 'FACULTY',
            officeHours: '',
            photo: ''
        });
        setShowStudentForm(false);
        setIsUpdating(false);
        setIsAddingFaculty(false);
    };

    const addingFaculty = () => {
        resetForm(); 
        setIsAddingFaculty(true);
        setShowStudentForm(true);
    };
    const addingStudent=()=>{
        resetForm(); 
        setIsAddingFaculty(false);
        setShowStudentForm(true);

    }

    const handleDelete = async (id, type) => {
        try {
            await axios.delete(`http://localhost:8081/college/${type}/${id}`);
            type === 'student' ? fetchStudentsByDeptAndYear() : fetchFacultiesByDept();
        } catch (error) {
            console.error(`Error deleting ${type}:`, error);
        }
    };

    const handleUpdate = (data, isFaculty) => {
        if (isFaculty) {
            setFacultyData(data);
            setIsUpdating(true);
            setIsAddingFaculty(true);
        } else {
            setStudentData(data);
            setIsUpdating(true);
            setIsAddingFaculty(false);
        }
        setShowStudentForm(true); 
    };

    return (
        <div className="admin-container">
            <h1>Administrator Dashboard</h1>
            <div className="filters">
                <label>
                    Department:
                    <input
                        type="text"
                        value={department}
                        onChange={(e) => setDepartment(e.target.value)}
                        required
                    />
                </label>
                <label>
                    Year:
                    <input
                        type="number"
                        value={year}
                        onChange={(e) => setYear(e.target.value)}
                        required
                    />
                </label>
                <label>{error && <p className="error-message">{error}</p>}</label>
                <button onClick={fetchStudentsByDeptAndYear}>Get Students</button>
                <button onClick={fetchFacultiesByDept}>Get Faculties</button>
            </div>

            <div className="button-group">
                <button onClick={ addingStudent}>Add Student</button>
                <button onClick={addingFaculty}>Add Faculty</button>
            </div>

            {students.length > 0 && (
                <>
                    <h2>Students</h2>
                    <ul className="student-list">
                        {students.map((student) => (
                            <li key={student.id} className="student-item">
                                <span>{student.fullName}</span>
                                <button onClick={() => handleDelete(student.id, 'student')}>Delete</button>
                                <button onClick={() => handleUpdate(student, false)}>Update</button>
                            </li>
                        ))}
                    </ul>
                </>
            )}

            {faculties.length > 0 && (
                <>
                    <h2>Faculties</h2>
                    <ul className="faculty-list">
                        {faculties.map((faculty) => (
                            <li key={faculty.id} className="faculty-item">
                                <span>{faculty.fullName}</span>
                                <button onClick={() => handleDelete(faculty.id, 'faculty')}>Delete</button>
                                <button onClick={() => handleUpdate(faculty, true)}>Update</button>
                            </li>
                        ))}
                    </ul>
                </>
            )}

            {showStudentForm && (
                <form className="student-form" onSubmit={isAddingFaculty ? handleFacultySubmit : handleStudentSubmit}>
                    <h2>{isUpdating ? `Update ${isAddingFaculty ? 'Faculty' : 'Student'}` : `Add ${isAddingFaculty ? 'Faculty' : 'Student'}`}</h2>
                    
                    {/* Full Name Input */}
                    <label>
                        Full Name:
                        <input
                            type="text"
                            name="fullName"
                            value={isAddingFaculty ? facultyData.fullName : studentData.fullName}
                            onChange={isAddingFaculty ? handleFacultyInputChange : handleStudentInputChange}
                            required
                        />
                    </label>
                   
                    <label>
                        Department:
                        <select
                            name="departmentId"
                            value={isAddingFaculty ? facultyData.departmentId : studentData.departmentId}
                            onChange={isAddingFaculty ? handleFacultyInputChange : handleStudentInputChange}
                        >
                            {departments.map((dept) => (
                                <option key={dept.id} value={dept.id}>{dept.name}</option>
                            ))}
                        </select>
                    </label>

                    {/* Phone Number Input */}
                    <label>
                        Phone Number:
                        <input
                            type="text"
                            name="phoneNumber"
                            value={isAddingFaculty ? facultyData.phoneNumber : studentData.phoneNumber}
                            onChange={isAddingFaculty ? handleFacultyInputChange : handleStudentInputChange}
                            required
                        />
                    </label>

                    {/* Email Input */}
                    <label>
                        Email:
                        <input
                            type="email"
                            name="email"
                            value={isAddingFaculty ? facultyData.email : studentData.email}
                            onChange={isAddingFaculty ? handleFacultyInputChange : handleStudentInputChange}
                            required
                        />
                    </label>

                    {/* Username Input */}
                    <label>
                        Username:
                        <input
                            type="text"
                            name="username"
                            value={isAddingFaculty ? facultyData.username : studentData.username}
                            onChange={isAddingFaculty ? handleFacultyInputChange : handleStudentInputChange}
                            required
                        />
                    </label>

                    {/* Password Input */}
                    <label>
                        Password:
                        <input
                            type="password"
                            name="password"
                            value={isAddingFaculty ? facultyData.password : studentData.password}
                            onChange={isAddingFaculty ? handleFacultyInputChange : handleStudentInputChange}
                            required
                        />
                    </label>

                    {/* Additional Fields for Faculty */}
                    {isAddingFaculty && (
                        <>
                            <label>
                                Office Hours:
                                <input
                                    type="text"
                                    name="officeHours"
                                    value={facultyData.officeHours}
                                    onChange={handleFacultyInputChange}
                                    required
                                />
                            </label>
                            <label>
                                Photo URL:
                                <input
                                    type="text"
                                    name="photo"
                                    value={facultyData.photo}
                                    onChange={handleFacultyInputChange}
                                    required
                                />
                            </label>
                        </>
                    )}

                    {/* Additional Fields for Student */}
                    {!isAddingFaculty && (
                        <>
                            <label>
                                Year:
                                <input
                                    type="number"
                                    name="year"
                                    value={studentData.year}
                                    onChange={handleStudentInputChange}
                                    required
                                />
                            </label>
                            <label>
                                Photo URL:
                                <input
                                    type="text"
                                    name="photo"
                                    value={studentData.photo}
                                    onChange={handleStudentInputChange}
                                    required
                                />
                            </label>
                        </>
                    )}

                    
                    <label>
                        Role:
                        <select
                            name="role"
                            value={isAddingFaculty ? facultyData.role : studentData.role}
                            onChange={isAddingFaculty ? handleFacultyInputChange : handleStudentInputChange}
                        >
                            <option value={isAddingFaculty ? 'FACULTY' : 'STUDENT'}>{isAddingFaculty ? 'FACULTY' : 'STUDENT'}</option>
                        </select>
                    </label>

                    <button type="submit">{isUpdating ? 'Update' : 'Add'} {isAddingFaculty ? 'Faculty' : 'Student'}</button>
                    <button type="button" onClick={resetForm}>Cancel</button>
                </form>
            )}
        </div>
    );
};

export default Administrator;
