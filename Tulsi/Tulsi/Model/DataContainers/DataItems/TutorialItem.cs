﻿using Xamarin.Forms;

namespace Tulsi.Model.DataContainers.DataItems {
    public class TutorialItem {
        public int Id { get; set; }

        public string Title { get; set; }
       
        public string SubTitle { get; set; }
      
        public string Description { get; set; }
        
        public ImageSource Icon { get; set; }
    }
}
