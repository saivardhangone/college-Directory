import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Login from '../src/Components/Login';
import Student from './Components/Student/Student';
import Administrator from '../src/Components/Administrator.js/Administrator';
import Faculty from '../src/Components/Faculty/Faculty';

function App() {
    return (
        <Router> {/* Ensure Router is wrapping all Routes */}
            <Routes>
                <Route path="/" element={<Login />} />
                <Route path="/Student" element={<Student />} />
                <Route path="/Administrator" element={<Administrator />} />
                <Route path="/faculty" element={<Faculty />} />
            </Routes>
        </Router>
    );
}

export default App;



