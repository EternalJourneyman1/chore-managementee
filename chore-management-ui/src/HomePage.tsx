import React from 'react';
import { Link } from 'react-router-dom';

export const HomePage = () => {
    return (
        <div style={{ backgroundColor: 'black', height: '100vh' }}>
            <div style={{ display: 'flex', justifyContent: 'center', alignItems: 'center', height: '100%' }}>
                <div style={{ backgroundColor: 'yellow', padding: '2rem', borderRadius: '1rem' }}>
                    <h1>Welcome to the Black and Yellow App!</h1>
                    <Link to="/login">
                        <button>Login</button>
                    </Link>
                </div>
            </div>
        </div>
    );
}

