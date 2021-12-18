<?php
defined('_JEXEC') or die;
//Load admin language file
$lang = JFactory::getLanguage();
$lang->load('com_quicknotice', JPATH_ADMINISTRATOR,$lang->getTag(),true);
$user        = JFactory::getUser();
$current_user_id = $user->id;
?>
<?php if(!QuicknoticeHelper::notallowed($this->permission)):?>
<?php
    $customform = QuicknoticeHelper::getFormName(JRequest::getVar('view', null, 'STR'));
    $filters    = QuicknoticeHelper::getListFilters($customform['view']);
?>
<script>
var messages = {
    delete_one: "<?php echo JText::_('COM_QUICKNOTICE_DELETE_THIS_ITEM');?>",
    delete_all: "<?php echo JText::_('COM_QUICKNOTICE_DELETE_ALL_ITEMS');?>",
    no_data: "<?php echo JText::_('COM_QUICKNOTICE_ALERT_NO_DATA');?>",
    delete_now:"<?php echo JText::_('COM_QUICKNOTICE_DELETE_NOW');?>",
    cancel:"<?php echo JText::_('COM_QUICKNOTICE_CANCEL');?>",
    alert_title_message:"<?php echo JText::_('COM_QUICKNOTICE_ALERT_MESSAGE');?>",
    alert_delete_message:"<?php echo JText::_('COM_QUICKNOTICE_ALERT_DELETE_MESSAGE');?>",
    alert_cancel_message:"<?php echo JText::_('COM_QUICKNOTICE_ALERT_CANCEL_MESSAGE');?>"
    
};
</script>
<link rel="stylesheet" href="<?php echo JURI::root().'components/com_quicknotice/assets/js/jquery-ui.css';?>">
<link rel="stylesheet" href="<?php echo JURI::root().'components/com_quicknotice/assets/css/editview/semantic.min.css';?>" type="text/css" />
<script src="<?php echo JURI::root().'components/com_quicknotice/assets/js/jquery-ui.js';?>"></script>
<script src="<?php echo JURI::root().'components/com_quicknotice/assets/js/listview/tablesorter.js';?>"></script>
<script type="text/javascript" src="<?php echo JURI::root().'components/com_quicknotice/assets/js/jquery.circliful.min.js';?>"></script>
<script src="<?php echo JURI::root().'components/com_quicknotice/assets/js/editview/semantic.min.js';?>"></script>
<script src="<?php echo JUri::root().'components/com_quicknotice/assets/js/jquery.rating.js';?>"></script>


<script src="<?php echo JUri::root().'components/com_quicknotice/assets/js/listview/datepicker.js';?>"></script>
<script src="<?php echo JUri::root().'components/com_quicknotice/assets/js/listview/datepicker.en.js';?>"></script>

<link href="<?php echo JUri::root().'components/com_quicknotice/assets/css/jquery.rating.css';?>" rel="stylesheet"/>
<link href="<?php echo JUri::root().'components/com_quicknotice/assets/css/datepicker.css';?>" rel="stylesheet"/>
<script type="text/javascript">
 Quicknotice(document).ready(function(){   
       VigentoTask.init();
    
   });
</script>
<div class="vg-list-view vg-task-list">

    <!-- End -->
    <div class="vg-table-container vg-viewcontent vg-clients-content">
            <div class="vg-listview-body-left col-md-4 col-lg-2 col-sm-4">
                  
                 <div class="datepicker-here" data-language='en'></div>
                    
            </div>
            <div class="vg-listview-body-right col-md-8 col-lg-10 col-sm-8">            
<div class="header-background"> </div>     
        <div class="vg-table-container-inner"> 

            <table class="vg-table-listclient vg-table-tasks">
                <thead>
                    <tr>
                        <th><div class="vg-thead-fixed">&nbsp;&nbsp;</div></th>
                        <th class="vg-action-list"><div class="vg-thead-fixed"><input type="checkbox" onclick="VigentoListView.checkListViewAllItems(this)" name="ids" class="vg-ids-list" value=""/><label>&nbsp;&nbsp;&nbsp;</label></div></th>
                        <th><div class="vg-thead-fixed">&nbsp;&nbsp;</div></th>
                        <th><div class="vg-thead-fixed"><span><?php echo JText::_('COM_QUICKNOTICE_TASKS'); ?></span></div></th>
                        <th><div class="vg-thead-fixed"><span><?php echo JText::_('COM_QUICKNOTICE_TASK_DEADLINE'); ?></span></div></th>
                        <th><div class="vg-thead-fixed"><span><?php echo JText::_('Items'); ?></span></div></th>
                        <th><div class="vg-thead-fixed"><span><?php echo JText::_('Assign To'); ?></span></div></th>
                        <th><div class="vg-thead-fixed"><span>Action</span></div></th>

                    </tr>
                </thead>
                    <!--Some records are demo data -->
                    <!--line 1-->
                <tbody class="vg-listview-tbody">
                 <?php if (count($this->items) > 0):
                    $count = 0;
                    foreach ($this->items as $item) : 
                        $count++;
                        $color = "";
                        $status = strtolower(trim($item->status));
                        switch($status)
                        {
                           case 'not started' :
                               $tag_status = 'vg-activity-task-newtask';
                               $tag_icon   = 'fa-play';
                              break;
                            case 'pending' :
                               $tag_status = 'vg-activity-task-progress';
                               $tag_icon   = 'fa-clock-o';
                              break;
                            case 'read' :
                               $tag_status = 'vg-activity-task-read';
                               $tag_icon   = 'fa-play';
                              break;
                            case 'in progress' :
                               $tag_status = 'vg-activity-task-progress';
                               $tag_icon   = 'fa-clock-o';
                              break;
                             case 'complete' :
                                $tag_status = 'vg-activity-task-complete';
                                $tag_icon   = 'fa-pause';
                              break;
                             case 'hold' :
                                $tag_status = 'vg-activity-task-hold';
                                $tag_icon   = 'fa-pause';
                              break;
                             case 'deadline' :
                                $tag_status = 'vg-activity-task-deadline';
                                 $tag_icon   = 'fa-clock-o';
                              break;
                        }

                  ?>
                    <tr align="right" height="90" class="<?php echo $tag_status.'-item';?>">
                        <td class="vg-activity-task-col-color"></td>
                        <td  class="vg-action-list"><input type="checkbox" value="<?php echo $item->id;?>"  name="cid" onclick="VigentoListView.checkListViewItem(this)"/><label>&nbsp;</label></td>
                        <td class="vg-number"><span class="vg-acttag <?php echo $tag;?>">&nbsp;</span><?php echo $item->task_no;?></td>                 
                        <td class="vg-clientname" >
                         <?php $name = QuicknoticeHelper::convertAvailableData($lang->getTag(),'title','title_ar',$item);?>
                        <a data-toggle="tooltip" title="<?php echo $name;?>" href="<?php echo JURI::root().'index.php?option=com_quicknotice&view=task&id='.$item->id;?>" 
                        data-html=""><span><?php echo $name;?></span></a>
                         <!--Popup-Hover-->
                            <div class='vg-task-info-hover '>                       
                                    <div class='vg-task-info'>
                                        <div class='vg-task-avatar'>
                                            <?php if($item->avatar) :?>
                                            <img src='<?php echo $item->avatar;?>'>
                                            <?php else:?>
                                            <img src='templates/quicknotice/images/avatar_sb.png' alt=''/>
                                            <?php endif;?>
                                        </div>
                                        <div class='vg-task-content'>
                                            <p><span><?php echo JText::_('COM_QUICKNOTICE_TILE_FROM'); ?> : </span><?php if(isset($item->u_name)) echo $item->u_name; ?></p>
                                            <p><span><?php echo JText::_('COM_QUICKNOTICE_TITLE_DATE'); ?> : </span><?php echo date('d/m/Y',strtotime($item->datecreated)).'@'.date('H:i A',strtotime($item->datecreated));?></p>
                                            <p><span><?php echo JText::_('COM_QUICKNOTICE_TITLE_STATUS'); ?> : </span><?php echo $item->priority;?></p>
                                            <p>
                                                <span><?php echo JText::_('COM_QUICKNOTICE_TITLE_DEADLINE'); ?>&nbsp;:&nbsp;</span>
                                                <?php
                                                    $str_date = null;
                                                    if (isset($item->deadline_date) && $item->deadline_date != "0000-00-00" && !empty($item->deadline_date)) {
                                                        $str_date .= date('d/m/Y',strtotime($item->deadline_date));
                                                    }
                                                    
                                                    if (isset($item->deadline_time) && $item->deadline_time != "0000-00-00" && !empty($item->deadline_time)) {
                                                        $str_date .= '@'.date('H:i', strtotime($item->deadline_time));
                                                    }
                                                    
                                                ?>
                                                <?php echo $str_date;?>
                                            </p>
                                        </div>
                                    </div>
                                    <div class='vg-task-description'>
                                        <p><?php echo $item->comment;?></p>
                                    </div>
                                    <div class='jfx_arrow_boxup left'>&nbsp;</div>
                                </div>
                                <!--End-Popup-Hover-->
                        
                            
                        </td>
                        
                        <td class="vg-industry"><?php echo $item->deadline_date. '@'.$item->deadline_time; ?></td>
                        <td class="vg-items"><?php echo $item->total_items; ?></td>
                        <td class="vg-listview-author">
                           <?php $created_by_avatar = QuicknoticeHelper::getAvatar($item->assigned_to); ?>
                           <img alt="<?php echo $item->assigned_to;?>" src="<?php echo $created_by_avatar;?>"/>
                        </td>
                        <td class="vg-action">
                           <?php if($status!='complete'):?>
                           <div class="<?php echo $tag_status;?>"><a><i class="fa <?php echo $tag_icon;?>"></i><?php echo trim($item->status);?></a></div>
                           <?php else:?>
                            <div class="<?php echo $tag_status;?>">  
                              <span id="taskbutton<?php echo $item->id;?>" accesskey="<?php echo $item->id;?>">&nbsp;
                                             <?php if($current_user_id==$item->created_by):?>
                                                  <?php $creator = true; ?>
                                              <?php else:?>
                                                  <?php $creator = false;?>
                                              <?php endif;?>
                                           <input name="star<?php echo $item->id;?>" type="radio" value="1" <?php if($item->rating==1) echo 'checked';?> class="auto-submit-star" <?php if(!$creator) echo 'disabled="disabled"';?>/>
                                           <input name="star<?php echo $item->id;?>" type="radio" value="2" <?php if($item->rating==2) echo 'checked';?> class="auto-submit-star" <?php if(!$creator) echo 'disabled="disabled"';?>/>
                                           <input name="star<?php echo $item->id;?>" type="radio" value="3"<?php if($item->rating==3) echo 'checked';?> class="auto-submit-star" <?php if(!$creator) echo 'disabled="disabled"';?>/>
                                           <input name="star<?php echo $item->id;?>" type="radio" value="4" <?php if($item->rating==4) echo 'checked';?> class="auto-submit-star" <?php if(!$creator) echo 'disabled="disabled"';?>/>
                                           <input name="star<?php echo $item->id;?>" type="radio" value="5"  <?php if($item->rating==5) echo 'checked';?> class="auto-submit-star" <?php if(!$creator) echo 'disabled="disabled"';?>/>
                              </span>   
                            </div>  
                           <?php endif;?>
                        </td>
                        
                    </tr>
                   <?php endforeach;?>
                 <?php endif;?>
                </tbody>
                    <!--end line this show total records-->
                            
            </table>
            </div>
        </div>
 </div>   
    
</div>

<?php else:?>
     <?php echo JText::_('COM_QUICKNOTICE_YOU_NOT_ALLOWED_TO_ACCESS_THIS_MODULE');?>
<?php endif;?>