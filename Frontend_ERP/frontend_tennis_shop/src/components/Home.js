import React, { Component } from "react";

import SimpleImageSlider from "react-simple-image-slider";
import "../css/ImageSlider.css";

const images = [
  { url: "https://www.dmarge.com/wp-content/uploads/2021/05/Tennis-Racquets-Featured-Image-1-480x320.jpg" },
  { url: "https://pbs.twimg.com/media/CxUhYQPXAAA402O.jpg" },
  { url: "https://www.tennispro.eu/media/cms/Page/FR/nike/2022/TP_2022_S07_SIS7.jpg" },
  { url: "https://www.perfect-tennis.com/wp-content/uploads/2021/01/best-tennis-bags-780x470.jpg" }
];

const Home = () => {
  return (
    <div className="imageSlider">
      <SimpleImageSlider
        width={896}
        height={604}
        images={images}
        showBullets={true}
        showNavs={true}
      />
    </div>
  );
}

export default Home;