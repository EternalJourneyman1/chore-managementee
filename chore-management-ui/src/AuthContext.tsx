import React, {createContext, useState, useEffect, useContext} from 'react';
const Cookies = require('js-cookie');

type User = {
    id: string;
    username: string;
    role: string;
};

export const AuthContext = createContext<{ user: User | null; setUser: React.Dispatch<React.SetStateAction<User | null>> } | null>(null);

export const AuthContextProvider: React.FC<{ children: React.ReactNode }> = ({ children }) => {
    const [user, setUser] = useState<User | null>(null);

    useEffect(() => {
        if(!Cookies.get("SESSION")) return
        fetch('/api/user')
            .then((res) => res.json())
            .then((data) => setUser(data))
            .catch((error) => console.error(error));
    }, []);
/*
    if (!user) {
        return <div>Loading...</div>;
    }*/
    const newUser = {
        id: "2",
        username: "Test User",
        role: "USER"
    }
    return (
        <AuthContext.Provider value={{ user: newUser, setUser }}>
            {children}
        </AuthContext.Provider>
    );
};

export const useAuth = (): { user: User | null; setUser: React.Dispatch<React.SetStateAction<User | null>> } => {
    const auth = useContext(AuthContext);

    if (!auth) {
        throw new Error('useAuth must be used within an AuthContextProvider');
    }

    return auth;
};

