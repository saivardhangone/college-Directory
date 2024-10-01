import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import './Login.css';

function Login() {
    const [userName, setUserName] = useState('');
    const [password, setPassword] = useState('');
    const [roleValue, setRoleValue] = useState('');
    const [roles, setRoles] = useState([]); 
    const [error, setError] = useState('');
    const navigate = useNavigate(); 
    // Fetch roles from the backend
    const getRoles = async () => {
        try {
            const response = await fetch("http://localhost:8081/college/roles");
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            const data = await response.json(); 
            setRoles(data);
            setRoleValue(data[0]?.role || '');
        } catch (error) {
            console.error('Error fetching roles:', error);
            setError('Unable to load roles.');
        }
    };

    useEffect(() => {
        getRoles(); 
    }, []);

    
    const handleLogin = async (e) => {
        e.preventDefault();
        const role={role:roleValue }

        try {
            const response = await fetch('http://localhost:8081/college/authenticate', {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify({ userName, password, role: role })
            });
            const data = await response.json();
            console.log(data);

            if (data!=null) {
                setError('');
                
                if (roleValue === 'STUDENT') {
                    navigate('/Student', { state: { studentId: data.id } });
                } else if (roleValue === 'ADMINISTRATOR') {
                    navigate('/Administrator');
                } else if (roleValue === 'FACULTY') {
                    navigate('/faculty',{ state: { facultyId: data.id } });
                }
                alert(`Login successful! Redirecting to ${roleValue} dashboard...`);
            } else {
                setError('Invalid credentials. Please try again.');
            }
        } catch (error) {
            setError('An error occurred during login. Please try again.');
        }
    };

    return (
        <div className="login-container">
            <h1>Login</h1>
            <form onSubmit={handleLogin}>
                <div className="input-group">
                    <label htmlFor="username">Username</label>
                    <input
                        type="text"
                        id="username"
                        value={userName}
                        onChange={(e) => setUserName(e.target.value)}
                        required
                    />
                </div>
                <div className="input-group">
                    <label htmlFor="password">Password</label>
                    <input
                        type="password"
                        id="password"
                        value={password}
                        onChange={(e) => setPassword(e.target.value)}
                        required
                    />
                </div>
                <div className="input-group">
                    <label htmlFor="role">Role</label>
                    <select
                        id="role"
                        value={roleValue}
                        onChange={(e) => setRoleValue(e.target.value)}
                        required
                    >
                        {roles.length > 0 ? (
                            roles.map((r) => (
                                <option key={r.role} value={r.role}>
                                    {r.role}
                                </option>
                            ))
                        ) : (
                            <option>Loading roles...</option>
                        )}
                    </select>
                </div>
                {error && <p className="error-message">{error}</p>}
                <button type="submit" className="login-button">Login</button>
            </form>
        </div>
    );
}

export default Login;
