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
    const SESSION = Cookies.get("SESSION") || null

    useEffect(() => {
        if(!SESSION) return
        fetch('/api/user')
            .then((res) => res.json())
            .then((data) => setUser(data))
            .catch((error) => console.error(error));
    }, []);

    if (!user) {
        return <div>Loading...</div>;
    }

    return (
        <AuthContext.Provider value={{ user, setUser }}>
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

