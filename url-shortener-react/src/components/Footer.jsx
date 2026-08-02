import React from "react";
import { FaFacebook, FaTwitter, FaInstagram, FaLinkedin } from "react-icons/fa";

const Footer = () => {
  return (
    <footer className="bg-footer-gradient text-white py-8 z-40 relative border-t border-white/10">
      <div className="container mx-auto px-6 lg:px-14 flex flex-col lg:flex-row lg:justify-between items-center gap-4">
        <div className="text-center lg:text-left">
          <h2 className="text-3xl font-bold mb-2">BlinkURL</h2>
          <p className="text-slate-200">Simplifying URL shortening for efficient sharing</p>
        </div>

        <p className="mt-4 lg:mt-0">
          &copy; 2026 BlinkURL. All rights reserved.
        </p>

        <div className="flex space-x-6 mt-4 lg:mt-0">
          <a href="#" className="text-slate-200 hover:text-white transition-colors duration-150">
            <FaFacebook size={24} />
          </a>
          <a href="#" className="text-slate-200 hover:text-white transition-colors duration-150">
            <FaTwitter size={24} />
          </a>
          <a href="#" className="text-slate-200 hover:text-white transition-colors duration-150">
            <FaInstagram size={24} />
          </a>
          <a href="#" className="text-slate-200 hover:text-white transition-colors duration-150">
            <FaLinkedin size={24} />
          </a>
        </div>
      </div>
    </footer>
  );
};

export default Footer;