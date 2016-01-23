function checkEmpty(data)
{
	if(data.length==0||data=="")
		{
		return false;
		}
	return true;
}

function checkCheckBox(arr)
{
	flag=0;
	for(i=0;i<=arr.length;i++)
		{
		if(arr[i].checked)
			{
			flag=1;
			break;
			}
		}
	if(flag==0)
		{
		return false;
		}
	return true;
}

function checkRadioButton(arr)
{
	flag=0;
	for(i=0;i<=arr.length;i++)
		{
		if(arr[i].checked)
			{
			flag=1;
			break;
			}
		}
	if(flag==0)
		{
		return false;
		}
	return true;
}

