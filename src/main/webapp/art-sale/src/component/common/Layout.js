import { Fragment } from 'react';

import Navbar from './Navbar';

const Layout = (props) => {
  return (
    <Fragment>
      <Navbar/>
      {console.log(props.name)}
      <main>{props.children}</main>
    </Fragment>
  );
};

export default Layout;
