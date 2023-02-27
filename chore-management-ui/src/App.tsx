import React from 'react';
import './App.css';
import {AppRoutes} from './Routes';
import {AuthContextProvider} from "./AuthContext";

function App() {
    return (
        <AuthContextProvider>
            <div>
                <AppRoutes/>
            </div>
        </AuthContextProvider>
    );
}

export default App;
