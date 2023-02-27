const { createProxyMiddleware } = require('http-proxy-middleware');
const Cookies = require('js-cookie');


module.exports = function(app) {
    app.use('/api', createProxyMiddleware({
        target: 'http://localhost:8080',
        changeOrigin: true,
        cookieDomainRewrite: 'localhost',
        secure: false,
        onProxyRes: function(proxyRes, req, res) {
            if (proxyRes.headers['set-cookie']) {
                const cookies = proxyRes.headers['set-cookie'].map(cookie => cookie.split(';')[0]);
                cookies.forEach(cookie => {
                    const [name, value] = cookie.split('=');
                    Cookies.set(name, value);
                });
            }
        }
    }));

    app.get('/', function(req, res, next) {
        const session = Cookies.get('SESSION');
        if (!session) {
            res.redirect('http://localhost:3000/chores');
        }
        next()
    });
};
