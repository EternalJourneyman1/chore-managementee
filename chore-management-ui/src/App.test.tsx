import React from 'react';
import {render, screen} from '@testing-library/react';
import App from './App';

test('renders Loading... message', () => {
  render(<App />);
  const loadingMessage = screen.getByText(/Loading.../i);
  expect(loadingMessage).toBeInTheDocument();
});
