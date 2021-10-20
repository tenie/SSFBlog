<div class="container" style="font-size: 16px;">
	<div class="row">
		<div class="col-lg-8 col-lg-offset-2 col-md-10 col-md-offset-1">
			<ul class="list-inline text-center">
				<#if twitter !="">
				<li><a href="${twitter}" target="_blank"> <span
						class="fa-stack fa-lg"> <i class="fa fa-circle fa-stack-2x"></i>
							<i class="fa fa-twitter fa-stack-1x fa-inverse"></i>
					</span>
				</a></li>
				</#if>
				<#if facebook !="">
				<li><a href="${facebook}" target="_blank"> <span
						class="fa-stack fa-lg"> <i class="fa fa-circle fa-stack-2x"></i>
							<i class="fa fa-facebook fa-stack-1x fa-inverse"></i>
					</span>
				</a></li>
				</#if>
				<#if github !="">
				<li><a href="${github}" target="_blank"> <span
						class="fa-stack fa-lg"> <i class="fa fa-circle fa-stack-2x"></i>
							<i class="fa fa-github fa-stack-1x fa-inverse"></i>
					</span>
				</a></li>
				</#if>
				<#if weixin !="">
				<li><a href="${weixin}" target="_blank"> <span
						class="fa-stack fa-lg"> <i class="fa fa-circle fa-stack-2x"></i>
							<i class="fa fa-weixin fa-stack-1x fa-inverse"></i>
					</span>
				</a></li>
				</#if>

			</ul>
			<#if Copyright !="">
			<p class="copyright text-muted">Copyright &copy;${Copyright}
			</#if>

			<#if beianNo !=""> <a href="http://www.beian.miit.gov.cn"
				target="_blank"> ${beianNo} </a> </#if>
			</p>
		</div>
	</div>
</div>



