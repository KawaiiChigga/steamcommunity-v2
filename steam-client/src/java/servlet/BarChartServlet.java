package servlet;

/***********************************************************************************************
 * File Info: $Id: BarChartServlet.java,v 1.5 2003/04/19 01:41:27 nathaniel_auvil Exp $
 * Copyright (C) 2002
 * Author: Nathaniel G. Auvil
 * Contributor(s):
 *
 * Copyright 2002 (C) Nathaniel G. Auvil. All Rights Reserved.
 *
 * Redistribution and use of this software and associated documentation ("Software"), with or
 * without modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain copyright statements and notices.
 * 	Redistributions must also contain a copy of this document.
 *
 * 2. Redistributions in binary form must reproduce the above copyright notice, this list of
 * 	conditions and the following disclaimer in the documentation and/or other materials
 * 	provided with the distribution.
 *
 * 3. The name "jCharts" or "Nathaniel G. Auvil" must not be used to endorse or promote
 * 	products derived from this Software without prior written permission of Nathaniel G.
 * 	Auvil.  For written permission, please contact nathaniel_auvil@users.sourceforge.net
 *
 * 4. Products derived from this Software may not be called "jCharts" nor may "jCharts" appear
 * 	in their names without prior written permission of Nathaniel G. Auvil. jCharts is a
 * 	registered trademark of Nathaniel G. Auvil.
 *
 * 5. Due credit should be given to the jCharts Project (http://jcharts.sourceforge.net/).
 *
 * THIS SOFTWARE IS PROVIDED BY Nathaniel G. Auvil AND CONTRIBUTORS ``AS IS'' AND ANY
 * EXPRESSED OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF
 * MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL
 * jCharts OR ITS CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION)
 * HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT,STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN
 * IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE
 ************************************************************************************************/




import client.JerseyClient;
import org.jCharts.axisChart.AxisChart;
import org.jCharts.axisChart.customRenderers.axisValue.renderers.ValueLabelPosition;
import org.jCharts.axisChart.customRenderers.axisValue.renderers.ValueLabelRenderer;
import org.jCharts.chartData.AxisChartDataSet;
import org.jCharts.chartData.DataSeries;
import org.jCharts.chartData.interfaces.IAxisDataSeries;
import org.jCharts.encoders.ServletEncoderHelper;
import org.jCharts.properties.*;
import org.jCharts.properties.util.ChartFont;
import org.jCharts.types.ChartType;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.awt.*;
import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

@WebServlet(name = "BarChartServlet", urlPatterns = {"/BarChartServlet"})
public class BarChartServlet extends HttpServlet
{
	//---all of my charts serviced by this Servlet will have the same properties.
	private BarChartProperties barChartProperties;

	//---all of my charts serviced by this Servlet will have the same properties.
	protected LegendProperties legendProperties;
	protected AxisProperties axisProperties;
	protected ChartProperties chartProperties;

	protected int width = 1360;
	protected int height = 720;


	/**********************************************************************************************
	 *
	 **********************************************************************************************/
	public void init()
	{
		this.legendProperties = new LegendProperties();
		this.chartProperties = new ChartProperties();
		this.axisProperties = new AxisProperties( false );
		ChartFont axisScaleFont = new ChartFont( new Font( "Lato", Font.PLAIN, 13 ), Color.black );
		axisProperties.getXAxisProperties().setScaleChartFont( axisScaleFont );
		axisProperties.getYAxisProperties().setScaleChartFont( axisScaleFont );

		ChartFont axisTitleFont = new ChartFont( new Font( "Arial", Font.PLAIN, 18 ), Color.black );
		axisProperties.getXAxisProperties().setTitleChartFont( axisTitleFont );
		axisProperties.getYAxisProperties().setTitleChartFont( axisTitleFont );

		DataAxisProperties dataAxisProperties = (DataAxisProperties) axisProperties.getYAxisProperties();

		try
		{
			dataAxisProperties.setUserDefinedScale( 0, 1 );
		}
		catch( PropertyException propertyException )
		{
			propertyException.printStackTrace();
		}

		dataAxisProperties.setRoundToNearest( 1 );

		ChartFont titleFont = new ChartFont( new Font( "Lato", Font.PLAIN, 36 ), Color.black );
		this.chartProperties.setTitleFont( titleFont );

		this.barChartProperties = new BarChartProperties();

		ValueLabelRenderer valueLabelRenderer = new ValueLabelRenderer( false, false, true, -1 );
		valueLabelRenderer.setValueLabelPosition( ValueLabelPosition.ON_TOP );
		valueLabelRenderer.useVerticalLabels( false );
		barChartProperties.addPostRenderEventListener( valueLabelRenderer );

	}


	/**********************************************************************************************
	 *
	 **********************************************************************************************/
	public void service( HttpServletRequest req, HttpServletResponse httpServletResponse ) throws ServletException, IOException
	{
		try
		{
                        JerseyClient jc = new JerseyClient();
                        JSONArray arr = (JSONArray) JSONValue.parse(jc.getAllDiscussion());
			String[] xAxisLabels = new String[arr.size()];
                        double data[][] = new double[1][arr.size()];
                        int count;
                        for(int i=0;i<arr.size();i++){
                            xAxisLabels[i] = new String();
                            xAxisLabels[i] = ((JSONObject) arr.get(i)).get("gamename").toString();
                            String disid =  ((JSONObject) arr.get(i)).get("discussionid").toString();
                            count=0;
                            JSONArray posts1 = (JSONArray) JSONValue.parse(jc.getAllThread(disid, "1"));
                            JSONArray posts2 = (JSONArray) JSONValue.parse(jc.getAllThread(disid, "2"));
                            count=posts1.size()+posts2.size();
                            data[0][i] = count;
                        }
			String xAxisTitle = "Discussions";
			String yAxisTitle = "Posts";
			String title = "Steam Discussion Chart";
			IAxisDataSeries dataSeries = new DataSeries( xAxisLabels, xAxisTitle, yAxisTitle, title );

//			double[][] data = new double[][]{{1500, 6880, 4510, 2600,1000, 5000}};
			String[] legendLabels = {"Number of Posts"};
			Paint[] paints = new Paint[]{Color.yellow};
			dataSeries.addIAxisPlotDataSet( new AxisChartDataSet( data, legendLabels, paints, ChartType.BAR, this.barChartProperties ) );

			AxisChart axisChart = new AxisChart( dataSeries, this.chartProperties, this.axisProperties, this.legendProperties, this.width, this.height );
			ServletEncoderHelper.encodeJPEG13( axisChart, 1.0f, httpServletResponse );
                        
		}
		catch( Throwable throwable )
		{
			//HACK do your error handling here...
			throwable.printStackTrace();
		}
	}
}
