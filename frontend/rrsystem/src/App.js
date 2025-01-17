import React from 'react';

import Header from './ui/components/header/Header';
import Footer from './ui/components/footer/Footer';
import Login from './pages/auth/Login';

function App() {
  return (
    <div className="App">
      <Header/>
      <Login/>
      <Footer/>
    </div>
  );
}

export default App;
