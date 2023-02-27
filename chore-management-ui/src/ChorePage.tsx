import React from 'react';
import {Link} from "react-router-dom";

export const ChorePage = () => {
    const today = new Date().toLocaleDateString();

    return (
        <div className={'container'}>
            <h1>Chore Page</h1>
            <p>Today's Date: {today}</p>
            <Link to="/add-chore"><button>Add Chore</button></Link>
        </div>
    );
};

