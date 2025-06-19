import './App.css'

import { BrowserRouter, Routes, Route } from 'react-router-dom'
import DefaultLayout from './Layout/DefaultLayout'
import HomePage from './Pages/HomePage'
import GamesPage from './Pages/GamesPage'
import GameDetail from './Pages/GameDetail';
import GenresPage from './Pages/GenresPage'
import PlatformDetail from './Pages/PlatformDetail'
import PlatformsPage from './Pages/PlatformsPage'
import AboutUs from './Pages/AboutUs'
import NotFound from './Pages/NotFound'
import 'bootstrap/dist/css/bootstrap.min.css';
import GenreDetail from './Pages/GenreDetail'

function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path='/' element={<DefaultLayout />}>
          <Route index element={<HomePage />} />
          <Route path='games' element={<GamesPage />} />
          <Route path="games/search" element={<GamesPage />} />
          <Route path='games/:id' element={<GameDetail />} />
          <Route path="platforms" element={<PlatformsPage />} />
          <Route path='platforms/:id' element={<PlatformDetail />} />
          <Route path="genres" element={<GenresPage />} />
          <Route path='genres/:id' element={<GenreDetail />} />
          <Route path='aboutus' element={<AboutUs />} />
          <Route path='*' element={<NotFound />} />
        </Route>
      </Routes>
    </BrowserRouter>
  )
}

export default App


