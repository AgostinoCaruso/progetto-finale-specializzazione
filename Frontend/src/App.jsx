import './App.css'

import { BrowserRouter, Routes, Route } from 'react-router-dom'
import DefaultLayout from './Layout/DefaultLayout'
import HomePage from './Pages/HomePage'
import GamesPage from './Pages/GamesPage'
import GameDetail from './Pages/GameDetail';
import PlatformDetail from './Pages/PlatformDetail'
import 'bootstrap/dist/css/bootstrap.min.css';

function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path='/' element={<DefaultLayout />}>
          <Route index element={<HomePage />} />
          <Route path='games' element={<GamesPage />} />
          <Route path="games/search" element={<GamesPage />} />
          <Route path='games/:id' element={<GameDetail />} />
          <Route path='platforms/:id' element={<PlatformDetail />} />
          <Route path='genres/:id' element={<PlatformDetail />} />
        </Route>
      </Routes>
    </BrowserRouter>
  )
}

export default App


