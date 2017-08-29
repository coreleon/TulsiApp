﻿using Syncfusion.SfChart.XForms;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Tulsi.NavigationFramework;
using Tulsi.ViewModels;
using Tulsi.ViewModels.Content;
using Xamarin.Forms;
using Xamarin.Forms.Xaml;

namespace Tulsi.Pages.Content {
    [XamlCompilation(XamlCompilationOptions.Compile)]
    public partial class BuyerView : ContentView, IView {

        private readonly BuyerViewModel _viewModel;

        public BuyerView() {
            InitializeComponent();

            DashboardViewModel dvm = new DashboardViewModel();

            SfChart chart = new SfChart();
            DoughnutSeries doughnutSeries = new DoughnutSeries() {
                ItemsSource = dvm.ChartData,
                XBindingPath = "Name",
                YBindingPath = "Value",
                DoughnutCoefficient = 0.7,
                ExplodeIndex = 0
            };
            List<Color> colors = new List<Color>()
            {
                Color.FromHex("#82DA69"),
                Color.FromHex("#E47132"),
                Color.FromHex("#9EE5FC"),
            };

            doughnutSeries.ColorModel.Palette = ChartColorPalette.Custom;
            doughnutSeries.ColorModel.CustomBrushes = colors;
            chart.WidthRequest = 180;
            chart.HeightRequest = 180;
            //chart.BackgroundColor = Color.FromHex("#F3F3F3");
            chart.Series.Add(doughnutSeries);

            chart.Title.TextColor = Color.FromHex("#cccccc");
            chart.HorizontalOptions = LayoutOptions.Center;
            chart.VerticalOptions = LayoutOptions.Center;
            ChartGrid.Children.Add(chart);

            StackLayout MiddleStack = new StackLayout() {
                VerticalOptions = LayoutOptions.Center,
                HorizontalOptions = LayoutOptions.Center,
                BackgroundColor = Color.White
            };
            Label MiddleText1 = new Label() {
                Text = "23%",
                FontSize = 20,
                FontAttributes = FontAttributes.Bold

            };
            Label MiddleText2 = new Label() {
                Text = "mobile",
                FontSize = 10,
                FontAttributes = FontAttributes.Bold

            };
            MiddleStack.Children.Add(MiddleText1);
            MiddleStack.Children.Add(MiddleText2);
            ChartGrid.Children.Add(MiddleStack);

            BindingContext = _viewModel = new BuyerViewModel();
        }

        public void ApplyVisualChangesWhileNavigating() {
            _viewModel.Dispose();
        }
    }
}