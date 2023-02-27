import React from 'react';
import {Route, Routes} from 'react-router-dom';
import {LandingPage} from "./LandingPage";
import {ProfilePage} from "./ProfilePage";
import {ChorePage} from "./ChorePage";
import {AddChorePage} from "./AddChorePage";

export const  AppRoutes = () =>  {
    return (
        <Routes>
            <Route path="/landing-page" element={<LandingPage />}/>
            <Route path="/chores" element={<ChorePage />}/>
            <Route path="/add-chore" element={<AddChorePage />}/>
            <Route path="/profile" element={<ProfilePage />}/>
            <Route path="/" element={<ChorePage/>}/>
         </Routes>
    );
}
