﻿using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Tulsi.NavigationFramework;
using Xamarin.Forms;
using Tulsi.ViewModels;
using SlideOverKit;
using Tulsi.Helpers;

namespace Tulsi {
    public partial class SearchPage : ContentPage, IView {

        private readonly SearchPageViewModel _viewModel;

        /// <summary>
        ///     ctor().
        /// </summary>
        public SearchPage() {
            InitializeComponent();

            BindingContext = _viewModel = new SearchPageViewModel();

            _viewModel.Spot = spot_ContentView;
        }

        /// <summary>
        ///     Make some visual changes of current page through navigating process (hide side menu or smt...)
        /// </summary>
        public void ApplyVisualChangesWhileNavigating() {
        }

        /// <summary>
        ///     Occurs only for Android (not for iOS).
        ///     False navigate out from page, true - staying in this page.
        /// </summary>
        /// <returns></returns>
        protected override bool OnBackButtonPressed() {
            if (_viewModel.ImportedView != null) {
                _viewModel.NativeSenderCloseView();
                return true;
            } else {
                BaseSingleton<ViewSwitchingLogic>.Instance.NavigateOneStepBack();
                return true;
            }
        }

        public void Dispose() {
            _viewModel.Dispose();
        }
    }
}
