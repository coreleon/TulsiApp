﻿using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Xamarin.Forms;

namespace Tulsi.NavigationFramework {
    public sealed class ViewContainer {
        private static readonly string _ERROR_CANT_GET_VIEW_IN_NAVIGATION_FRAME_BY_TYPE = "Can't get view in navigation frame by it's type";
        private static readonly string _ERROR_CANT_GET_VIEW_BY_TYPE = "Can't get view by it's type";

        /// <summary>
        /// Container for views wrapped by Xamarin.Forms.NavigationPage
        /// </summary>
        private readonly Dictionary<ViewType, Func<Page>> _viewsInNavigationFrame;

        /// <summary>
        /// Container for views
        /// </summary>
        private readonly Dictionary<ViewType, Func<IView>> _views;

        /// <summary>
        /// Public ctor
        /// </summary>
        public ViewContainer() {
            _views = buildViews();
            _viewsInNavigationFrame = buildViewsInNavigationFrames();
        }

        /// <summary>
        /// Get view in navigation frame by type.
        /// </summary>
        /// <param name="viewType"></param>
        /// <returns></returns>
        public Page GetViewInNavigationFrameByType(ViewType viewType) {
            try {
                return _viewsInNavigationFrame[viewType]();
            }
            catch (Exception) {
                throw new InvalidOperationException(string.Format("ViewContainer.GetViewInNavigationFrameByType - {0}", _ERROR_CANT_GET_VIEW_IN_NAVIGATION_FRAME_BY_TYPE));
            }
        }

        /// <summary>
        /// Get view by type
        /// </summary>
        /// <param name="viewType"></param>
        /// <returns></returns>
        public IView GetViewByType(ViewType viewType) {
            try {
                return _views[viewType]();
            }
            catch (Exception) {
                throw new InvalidOperationException(string.Format("ViewContainer.GetViewByType - {0}", _ERROR_CANT_GET_VIEW_BY_TYPE));
            }
        }

        /// <summary>
        /// Build views wrapped by Xamarin.Forms.NavigationPage
        /// </summary>
        /// <returns></returns>
        private Dictionary<ViewType, Func<Page>> buildViewsInNavigationFrames() {
            return new Dictionary<ViewType, Func<Page>>() {
                {
                    ViewType.LoginPage,
                    () => new ViewBuider<LoginPage>().GetViewInNavigationFrame()
                },
                {
                    ViewType.DashboardPage,
                    () => new ViewBuider<DashboardPage>().GetViewInNavigationFrame()
                },
                {
                    ViewType.PasswordRecoveryPage,
                    () => new ViewBuider<PasswordRecoveryPage>().GetViewInNavigationFrame()
                }
            };
        }

        /// <summary>
        /// Build views
        /// </summary>
        /// <returns></returns>
        private Dictionary<ViewType, Func<IView>> buildViews() {
            return new Dictionary<ViewType, Func<IView>>() {
                {
                    ViewType.LoginPage,
                    () => new ViewBuider<LoginPage>().GetView()
                },
                {
                    ViewType.DashboardPage,
                    () => new ViewBuider<DashboardPage>().GetView()
                },
                {
                    ViewType.PasswordRecoveryPage,
                    () => new ViewBuider<PasswordRecoveryPage>().GetView()
                }
            };
        }
    }
}