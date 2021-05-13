import { Fragment } from 'react';

import Navbar from './Navbar';

const Layout = (props) => {
  return (
    <Fragment>
      <div className="outer">
        <Navbar/>
        {console.log(props.name)}
        <main>{props.children}</main>
      </div>
    </Fragment>
  );
};

export default Layout;
